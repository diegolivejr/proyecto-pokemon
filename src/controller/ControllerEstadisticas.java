package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;
import util.UtilView;

public class ControllerEstadisticas extends ControllerWithAttributes {
	
	@FXML VBox box;
	@FXML Label nombre;
	@FXML Label mote;
	@FXML Label nivel;
	@FXML Label vida;
	@FXML Label ataque;
	@FXML Label defensa;
	@FXML Label velocidad;
	@FXML Label atEspecial;
	@FXML Label deEspecial;
	@FXML Label sexo;
	
	
	Pokemon pokemon;
	int desdeEquipo;
	
    public void initialize() {
    	//if (pokemon==null) pokemon = Entrenador.getEntrenadorActual().getPokemonElegido();    
    }

	@Override
	public void initializeAttributes(Object data) {
    	this.pokemon =(Pokemon)data;
    	this.desdeEquipo = pokemon.getCaja();
		actualizaDatos();		
	}    

    public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	void cerrar() {
    	((Stage)box.getScene().getWindow()).close();
    }
		
	
	@FXML
	public void addEquipo() {		
		String nombre = pokemon.getMote();
		String texto = (desdeEquipo==0)? "al PC" : "al equipo";
		if (UtilView.confirmAlert("Mover Pokemon","¿Quieres mover a "+nombre+" "+texto+"?")) {
			if (Entrenador.getEntrenadorActual().switchPokemon(pokemon))
				UtilView.showInfo("Proyecto Pokemon", "Has movido tu pokemon "+texto+".");
			else
				UtilView.showError("Proyecto Pokemon", "No se ha podido mover tu pokemon "+texto+".");
		}		
		cerrar();
	} 
	  

	@FXML
	public void vender() {
		String nombre = pokemon.getMote();
		if (UtilView.confirmAlert("Liberar Pokemon","¿De verdad quieres vender a "+nombre+"?")) {
			Entrenador entrenador = Entrenador.getEntrenadorActual();
			int dinero = entrenador.venderPokemon(pokemon.getIdPokemon());
			int dineroEntrenador=Entrenador.getEntrenadorActual().getDinero();				
			UtilView.showInfo("Proyecto Pokemon", "Tu pokemon ha sido vendido. Ahora está encerrado, asustado y triste tu "+nombre+" te han dado por el "+UtilView.formateaDinero(dinero)+" PokeDólares. Ahora tienes "+UtilView.formateaDinero(dineroEntrenador)+" PokeDólares en tu monedero.");
		}
		cerrar();
	}
	
	@FXML
	public void liberar() {
		String nombre = pokemon.getMote();
		if (UtilView.confirmAlert("Liberar Pokemon","¿Quieres liberar a "+nombre+"?")) {
			Entrenador.getEntrenadorActual().liberarPokemon(pokemon.getIdPokemon());
			UtilView.showInfo("Proyecto Pokemon", "Se ha liberado el pokémon "+nombre);
		}
		cerrar();
	}
	

	public void actualizaDatos() {	
		if (pokemon==null) return;
		nombre.setText(pokemon.getNombre());
		mote.setText(pokemon.getMote());
		nivel.setText(Integer.toString(pokemon.getNivel()));
		vida.setText(Integer.toString(pokemon.getSalud()));
		ataque.setText(Integer.toString(pokemon.getAtaque()));
		defensa.setText(Integer.toString(pokemon.getDefensa()));
		velocidad.setText(Integer.toString(pokemon.getVelocidad()));
		atEspecial.setText(Integer.toString(pokemon.getAtEspecial()));    	
		atEspecial.setText(Integer.toString(pokemon.getAtEspecial()));    	
		deEspecial.setText(Integer.toString(pokemon.getDefEspecial()));    	
		sexo.setText(pokemon.getSexo());	
	}
	
}
