package controller;

import java.util.ArrayList;

import controller.ControllerCombate.ManejaMousePokemon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;
import util.UtilView;



public class ControllerElegirEquipo  extends ControllerWithAttributes {
	
	@FXML AnchorPane selector;

	Pokemon lista[];
	Pokemon equipo[];
	
	
    public void initialize() {
    	mostrarEquipo();
    }
	
	void cerrar() {
    	((Stage)selector.getScene().getWindow()).close();
    }

	
	public void mostrarEquipo() {
		int index = 0;
		for (Node nodo : selector.getChildren()) {
        	String nodoId = nodo.getId();
        	if (nodoId == null) { // es hueco para mostrar un pokemon
        		ImageView imageView = ((ImageView)nodo);
        		equipo = Entrenador.getEntrenadorActual().getEquipo();
        		imageView.setImage(null);
        		imageView.setOnMouseClicked(null);
        		if (index <= equipo.length -1) {
        			Pokemon pokemon = equipo[index];
        			Image image;
    				image = UtilView.getImagenDelante(pokemon.getNombre());
        			if (pokemon.getSalud()>=0) { // está vivo
	        			imageView.setImage(image);
	        			imageView.setOnMouseClicked(new ManejaMousePokemon(index));
        			}
        		}        			
        		index++;
        	}			
		} 		
	}
	
    // Maneja los clicks en los ImageViews
    class ManejaMousePokemon implements EventHandler<MouseEvent> {
    	
    	int index = -1;    	
    	
    	public ManejaMousePokemon(int index) {
			this.index = index;
		}

        @Override
        public void handle(MouseEvent event) {
            System.out.println("Se ha pulsado el pokemon "+index);
            lista[0] =equipo[index]; 
            cerrar();
            event.consume();
        }
    }

	@Override
	public void initializeAttributes(Object data) {
		lista = (Pokemon[]) data;
		
	};        			
	
	

}
