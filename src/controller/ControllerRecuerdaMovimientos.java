package controller;

import bbd.PokemonBD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import modelo.Entrenador;
import modelo.Movimiento;
import modelo.Pokemon;
import util.UtilView;

public class ControllerRecuerdaMovimientos {
	
	Pokemon pokemon;
	
	PokemonBD pkBD = new PokemonBD();
	
	@FXML ImageView imagen;
	@FXML HBox boxActivos;
	@FXML FlowPane boxAprendidos;
	
	@FXML
    public void equipo(javafx.event.ActionEvent event) {		
    	Pokemon[] resultado=new Pokemon[1];
    	UtilView.loadSceneModal("/vistas/PantallaElegirEquipo.fxml", "Elige un Pokémon",resultado);
    	pokemon=resultado[0];
    	refrescaVista();
	}
	
	private void refrescaVista() {
		if (pokemon!=null) {
			imagen.setImage(UtilView.getImagenDelante(pokemon.getNombre()));
			boxActivos.setSpacing(10);
			boxActivos.setAlignment(Pos.CENTER);
			boxActivos.getChildren().clear();
			for (Movimiento movimiento: pokemon.getMovimientosActivos()) {
				Button boton = new Button();
				boton.setText(movimiento.getNomMovimiento());
				boton.setOnAction(new ManejaClickBoton(movimiento));
				boton.setPrefHeight(60);
				boxActivos.getChildren().add(boton);
			}
			boxAprendidos.setAlignment(Pos.CENTER);
			boxAprendidos.getChildren().clear();
			for (Movimiento movimiento: pokemon.getMovimientosAprendidos()) {
				Button boton = new Button();
				boton.setText(movimiento.getNomMovimiento());
				boton.setOnAction(new ManejaClickBoton(movimiento));
				boton.setPrefHeight(40);
				boxAprendidos.getChildren().add(boton);
			}
		} else
			imagen.setImage(null);
	}
	
	private void activaDesactiva(Movimiento movimiento) {
		String activo=pokemon.activaDesactivaMovimiento(movimiento);
		if (activo!=null) {
			pkBD.actualizaMovimientoPokemon(movimiento.getIdMovimiento(), pokemon.getIdPokemon(), activo);
			refrescaVista();
		}		
	}
		
    // Maneja los clicks en los botones de movimientos de lucha
    class ManejaClickBoton implements EventHandler<ActionEvent> {
    	
    	Movimiento movimiento;    	
    	
    	public ManejaClickBoton(Movimiento movimiento) {
			this.movimiento = movimiento;
		}

		@Override
		public void handle(ActionEvent event) {
				activaDesactiva(movimiento);
	            event.consume();			
		}
    };        			
		
	
	@FXML
    public void atras(javafx.event.ActionEvent event) {
    	UtilView.mostrarMenuPrincipal(((Node) event.getSource()).getScene());
	}
}
