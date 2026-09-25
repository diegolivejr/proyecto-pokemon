package modelo;

import java.util.Random;

import bbd.BD;
import bbd.PokemonBD;

public class Combate {

	PokemonBD pkBD = new PokemonBD();
	
	Entrenador entrenador;
	Entrenador dominguero; // random

	Pokemon paladin;
	Pokemon rival;
	int numRival;
		
	int saludInicialPaladin;
	int saludInicialRival;
	
	
	public Combate(Entrenador entrenador) {
		this.entrenador = entrenador;
		this.dominguero = entrenador.generarOponente();
		/*
		if (entrenador.getCuantosEquipo()>0) {
			this.paladin = entrenador.getEquipo()[0];
			this.saludInicialPaladin = this.paladin.getSalud();
		}
		*/
		//this.paladin.setSalud(100000);
		this.numRival=0;
		this.rival = dominguero.getEquipo()[numRival];
		this.saludInicialRival = this.rival.getSalud();
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public Entrenador getDominguero() {
		return dominguero;
	}

	public void setDominguero(Entrenador dominguero) {
		this.dominguero = dominguero;
	}

	public Pokemon getPaladin() {
		return paladin;
	}

	public void setPaladin(Pokemon paladin) {
		if (paladin.getSalud()<=0) {
			System.out.println("El paladín seleccionado no tiene salud.");
			paladin=null;
		} else {
			System.out.println("Nuevo paladín: "+paladin.getNombre()+" con nivel: "+paladin.getNivel()+"  salud: "+paladin.getSalud()); 
			this.paladin = paladin;
			this.setSaludInicialPaladin(paladin.getSalud());			
		}
	}

	public Pokemon getRival() {
		return rival;
	}

	public void setRival(Pokemon rival) {
		this.rival = rival;
		this.setSaludInicialRival(rival.getSalud());
	}

	public int getNumRival() {
		return numRival;
	}

	public void setNumRival(int numRival) {
		this.numRival = numRival;
	}

	public int getSaludInicialPaladin() {
		return saludInicialPaladin;
	}

	public void setSaludInicialPaladin(int saludInicialPaladin) {
		this.saludInicialPaladin = saludInicialPaladin;
	}

	public int getSaludInicialRival() {
		return saludInicialRival;
	}

	public void setSaludInicialRival(int saludInicialRival) {
		this.saludInicialRival = saludInicialRival;
	}
	
	public double getPorcentajeSaludEntrenador() {
		if (this.paladin==null) return 0;
		double ahora = this.paladin.getSalud();
		double inicial = this.saludInicialPaladin;
		return ahora/inicial;
	}
	
	public double getPorcentajeSaludRival() {
		if (this.rival==null) return 0;
		double ahora = this.rival.getSalud();
		double inicial = this.saludInicialRival;
		return ahora/inicial;
	}
	
	public void movimientoEntrenador(Movimiento movimiento) {
		int potencia = movimiento.getPotencia();
		int salud = rival.getSalud() - potencia;		
		rival.setSalud(salud);
		System.out.println("La salud del "+rival.getNombre()+" rival es "+salud);
	}
	
	public void movimientoRival(Movimiento movimiento) {
		int potencia = movimiento.getPotencia();
		int salud = paladin.getSalud() - potencia;
		paladin.setSalud(salud);
		System.out.println("La salud de tu "+paladin.getMote()+" es "+salud);
	}
	
	// movimiento aleatorio del rival
	public Movimiento getMovimientoRival() {
		Random rnd = new Random();
		int index = rnd.nextInt(rival.getMovimientosActivos().size());
		return rival.getMovimientosActivos().get(index);		
	}
	
	
	public boolean siguientePokemonRival() {
		if (numRival++>=5) return false; // no le quedan más pokémons al rival		
		this.rival = dominguero.getEquipo()[numRival];
		this.saludInicialPaladin = this.paladin.getSalud();
		this.saludInicialRival = this.rival.getSalud();
		return true;		
	}
	
	public boolean quedanRivales() {
		Pokemon equipo[] = dominguero.getEquipo();
		for (int i=0; i<equipo.length;i++) {
			if (equipo[i].getSalud()>0) 
				return true;
		}
		return false;
	}

	public boolean quedanPaladines() {
		Pokemon equipo[] = entrenador.getEquipo();
		for (int i=0; i<equipo.length;i++) {
			if (equipo[i].getSalud()>0) 
				return true;
		}
		return false;
	}
	
	public Entrenador getGanador() {
		if (!quedanRivales()) {
			return entrenador;
		}
		if (!quedanPaladines()) {
			return dominguero;
		}
		return null;
	}
	
	public void subirExperienciaEntrenador() {
		for (int i=0; i<entrenador.getCuantosEquipo(); i++ ) {
			Pokemon pokemon = entrenador.getEquipo()[i];
			pokemon.subirExperiencia();
			pkBD.guarda(pokemon);
		}
	}
	
	public void robarCartera() {
		int nuevoDinero = entrenador.getDinero()+500; // fijo
		entrenador.setDinero(nuevoDinero);
		pkBD.actualizaDineroEntrenador(entrenador.getId(),entrenador.getDinero());
	}

}
