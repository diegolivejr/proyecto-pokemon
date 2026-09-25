package modelo;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.BooleanSupplier;

import bbd.BD;
import bbd.MovimientoBD;
import bbd.PokemonBD;
import bbd.PokedexBD;

public class Entrenador {
	
	private static Entrenador entrenadorActual = null;
	
	PokedexBD pdBD = new PokedexBD();
	PokemonBD pkBD = new PokemonBD();
	MovimientoBD mvBD = new MovimientoBD();
	
	public static Entrenador getEntrenadorActual() {
		return entrenadorActual;
	}
	
	public static void setEntrenadorActual(int id, String nombre, int dinero) {
		entrenadorActual = new Entrenador(id, nombre, dinero);		
	}

	int id;
	String nombre;
	int dinero;
	ArrayList<Pokemon> equipo;
	ArrayList<Pokemon> pc;

	
	public Entrenador(int id, String nombre, int dinero) {
		this.id = id;
		this.nombre = nombre;
		this.dinero = dinero;
		this.equipo = new ArrayList<Pokemon>();
		this.pc =  new ArrayList<Pokemon>();
	}
	
	public Entrenador() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public int getDinero() {
		return this.dinero;
	}
	
	public void setDinero(int dinero) {
		this.dinero = dinero;
		
	}

		
	public int getCuantosEquipo() {
		return getEquipo().length;
	}
	
	public int getCuantosPC() {
		return getPC().length;		
	}
	
	public Pokemon[] getEquipo() {
		return equipo.toArray(new Pokemon[0]);
	}

	public Pokemon[] getPC() {
		return pc.toArray(new Pokemon[0]);
	}
	
	
	
	public Pokemon nuevoPokemon(Pokedex pokemonCapturado, String mote) {		
		int cuantosEquipo = getEquipo().length;
		int cuantosPc = getPC().length;
		int valorCaja;
		
		if (cuantosEquipo<6) {
			valorCaja=0;
		} else if (cuantosPc<30) {
			valorCaja=1;			
		} else {
			System.out.println("No caben más pokémons");
			return null; // Demasiados pokemon
		}
		
		Pokemon pokemon = this.creaPokemonAleatorio(pokemonCapturado);
		pokemon.setIdEntrenador(id);
		pokemon.setCaja(valorCaja);
		String nuevoMote = (mote==null)? pokemon.getNombre() : mote;
		pokemon.setMote(nuevoMote);
		pokemon.setTipo1(pokemonCapturado.getTipo1());
		pokemon.setTipo2(pokemonCapturado.getTipo2());
		int pokemonId;
		if (id > 0) { // es un entrenador
			pokemonId = pkBD.crea(pokemon);
			if (pokemonId<=0) {
				System.out.println("Error al guardar el nuevo pokémon en la base de datos");
				return null;
			}
			pokemon.setIdPokemon(pokemonId);
			System.out.println("Nuevo pokemon: "+pokemonId+" es un "+pokemon.getNombre()+" y lo has llamado "+pokemon.getMote());
			pokemon.aprendeNuevosMovimientos();
	            
	        if (pokemonId>0) {
	        	this.recargaListaPokemon();
	    		return pokemon;
	        } else {
	        	return null;
	        }
		} else { // es un rival
			pokemonId=(this.getCuantosEquipo()+1)*(-1);
			pokemon.setIdPokemon(pokemonId);
			pokemon.aprendeNuevosMovimientos();
			equipo.add(pokemon);
    		return pokemon;
		}
		
				
	}
	
	
	// Optiene un pokemon aleatorio desde pokedex
	public Pokedex getRandomPokedex() {
		Pokedex pokedexCompleto[] = pdBD.getAllPokedex();				
		Random rnd = new Random();
		int aleatorio = rnd.nextInt(pokedexCompleto.length);
		return pokedexCompleto[aleatorio];
	}


	// Genera un pokemon con valores aleatorios
	private Pokemon creaPokemonAleatorio(Pokedex pokedex) {
		Random rand = new Random();
		Pokemon pokemon = new Pokemon();
		pokemon.setNumPokedex(pokedex.getId());
		pokemon.setNombre(pokedex.getNombre());
		pokemon.setMote(pokedex.getNombre());
		pokemon.setSexo((rand.nextInt(2)==0)?"M":"H");		
		pokemon.setFertilidad(5);		
		pokemon.setNivel(1);		
		// valores aleatorios para e le nivel 1
		pokemon.setSalud(rand.nextInt(26) + 25);			
		pokemon.setSaludMaxima(pokemon.getSalud());			
		pokemon.setAtaque(rand.nextInt(11) + 10);				
		pokemon.setDefensa(rand.nextInt(21) + 10);
		pokemon.setVelocidad(rand.nextInt(16) + 5);
		pokemon.setAtEspecial(rand.nextInt(11) + 10);
		pokemon.setDefEspecial(rand.nextInt(21) + 10);
		return pokemon;
	}

	public void addNuevosMovimientos(Pokemon pokemon) {
		ArrayList<Movimiento> movimientos = mvBD.getMovimientosNivel(pokemon.getNivel(), pokemon.getTipo1(), pokemon.getTipo2());
		for (Movimiento movimiento: movimientos) {
			String activo = pokemon.aprenderMovimiento(movimiento);
			if (activo!=null && pokemon.getIdPokemon()>0) { // el rival no guarda movimientos
				pkBD.guardaMovimientoPokemon(movimiento.getIdMovimiento(), pokemon.getIdPokemon(), activo );				
			};
		}
	}
		
	public void recargaListaPokemon() {		
		Pokemon[] pokemons = pkBD.getListaPokemonEntrenador(Entrenador.getEntrenadorActual().getId());
		setListaPokemon(pokemons);
	}
	
	public void setListaPokemon(Pokemon[] pokemons) {
		// Busco los del equipo
		equipo = new ArrayList<Pokemon>();
		for (int i=0; i<pokemons.length; i++) {
			if (pokemons[i].getCaja() == 0) {
				equipo.add(pokemons[i]);
			}
		}
		// Busco los del PC
		pc = new ArrayList<Pokemon>();
		for (int i=0; i<pokemons.length; i++) {
			if (pokemons[i].getCaja() == 1) {
				pc.add(pokemons[i]);
			}
		}		
	}
		
	public boolean switchPokemon(Pokemon pokemon) {
		if (pokemon==null) return false;
		int caja = pokemon.getCaja();
		int nuevaCaja=0;
		if (caja==0) nuevaCaja=1;		
		if (nuevaCaja==0 && this.getCuantosEquipo()>=6) return false;
		if (nuevaCaja==1 && this.getCuantosPC()>=30) return false;
		pokemon.setCaja(nuevaCaja);
		pkBD.guarda(pokemon);
		recargaListaPokemon();
		return true;
	}
	
	public void liberarPokemon(int id) {
		Pokemon pokemon = pkBD.getPokemon(id);
		pkBD.borra(pokemon);
		recargaListaPokemon();
	}

	public int venderPokemon(int id) {
		
		Pokemon pokemon = pkBD.getPokemon(id);
		pkBD.borra(pokemon);
		recargaListaPokemon();

		int precio = 1000;
		this.dinero = this.dinero + precio;
		
		pkBD.actualizaDineroEntrenador(this.id, this.dinero);
		return precio;
		
	}
	
	public void actualizaDineroEntrenador() {
	    PokemonBD pkBD = new PokemonBD();
	    pkBD.actualizaDineroEntrenador(this.id, this.dinero);
	}


	
	
	public void moverPC(Pokemon pokemon) {
		if (pokemon.getCaja()==0)
			switchPokemon(pokemon);
	}
	
	public void moverEquipo(Pokemon pokemon) {
		if (pokemon.getCaja()==1)
			switchPokemon(pokemon);		
	}
	
	public Pokemon entrenar(Pokemon pokemon) {
		return subirExperiencia(pokemon);
	}	
	
	private Pokemon subirExperiencia(Pokemon pokemon) {
		Random rnd = new Random();
		int experienciaActual = pokemon.getExperiencia();
		int aumento = rnd.nextInt(5)+11; // 5 a 15
		pokemon.setExperiencia(experienciaActual+aumento);
		pkBD.guarda(pokemon);
		return pokemon;
	}

	
	public Pokemon capturar(Pokedex pokemonCapturado, String mote) {
		return nuevoPokemon(pokemonCapturado, mote);
	}
	
	public void combatir() {
		Entrenador rival = generarOponente();
		System.out.println(rival.getId()+" - "+rival.getNombre());
		Pokemon lista[] = rival.getEquipo();
	}

	public int getNivelEquipo() {
		Pokemon equipo[] = getEquipo();
		int nivel=0;
		for (int i=0; i<equipo.length; i++) {
			int nuevoNivel = equipo[i].getNivel();
			if (nuevoNivel>nivel)
				nivel=nuevoNivel;
		}
		return nivel;
	}
	
	public Entrenador generarOponente() {
		Entrenador rival = new Entrenador(-1,"Domingureo", 0);
		int nivel=getNivelEquipo();
		for (int i=0; i<6; i++) {
			Pokemon nuevo = rival.nuevoPokemon(this.getRandomPokedex(), null);
			nuevo.setExperiencia((nivel-1)*10);
			nuevo.subirNivel();
			System.out.println("Nuevo pokemon rival: "+nuevo.getNombre()+" de nivel: "+nuevo.getNivel());
		}
		return rival;
	}
	
	
	public Pokemon criar(Pokemon padre, Pokemon madre, String moteHijo) {
		
		// comprobar requisitos para criar
		if (padre==null || madre==null) {
			System.out.println("El padre o la madre no pueden ser nulos.");
			return null;
		}
		if (padre.getFertilidad()<=0 || padre.getFertilidad()<=0) {
			System.out.println("El padre y la madre tienen que tener puntos de fertilidad.");
			return null;
		}
		if (padre.getSexo().equals(madre.getSexo())) {
			System.out.println("El padre y la madre tienen que tener distinto sexo.");
			return null;
		}
		if (!padre.getNombre().equals(madre.getNombre())) {
			System.out.println("El padre y la madre tienen que ser de la misma especie.");
			return null;
		}
		
		// generar nuevo pokemon aleatorio de la misma especie es lo mismo que capturar
		Pokedex pokedex = pdBD.getPokedexPorNombre(padre.getNombre());
		Pokemon hijo = nuevoPokemon(pokedex, moteHijo);		
		
		// quitar un punto de fertilidad al padre y a la madre
		padre.setFertilidad(padre.getFertilidad()-1);
		madre.setFertilidad(madre.getFertilidad()-1);
		
		return hijo;
		
	}
	
	public void curarEquipo() {
		Pokemon equipo[] = getEquipo();
		for (int i=0; i<equipo.length; i++) {
			equipo[i].curar();
			pkBD.guarda(equipo[i]);
		}
	}

	public static void setEntrenadorActual(Entrenador entrenador) {
		// TODO Auto-generated method stub
		
	}

	public void setRandomPokedex(Pokedex pokedex) {
		// TODO Auto-generated method stub
		
	}

	public BooleanSupplier equipoCurado() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPC(Pokemon[] pc2) {
		// TODO Auto-generated method stub
		
	}
		
}


