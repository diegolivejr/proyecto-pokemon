package controller;

import java.io.IOException;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.UtilView;
import modelo.Entrenador;
import modelo.Pokemon;
import modelo.Objeto;
import bbd.PokemonBD;

public class ControllerMochila {
    
	public void anillo() {
	    Entrenador entrenadorActual = Entrenador.getEntrenadorActual();
	    Objeto anillo = new Objeto(1, "Anillo Único", 0, 0, 0, 0, 4500);  // Id, nombre, ataques, defensas, precio

	    if (entrenadorActual.getDinero() >= anillo.getPrecio()) {
	        entrenadorActual.setDinero(entrenadorActual.getDinero() - anillo.getPrecio());
	        entrenadorActual.actualizaDineroEntrenador(); // Actualiza el dinero en la base de datos
	        System.out.println("Compra realizada. Dinero restante: " + entrenadorActual.getDinero());
	    } else {
	        System.out.println("Fondos insuficientes.");
	    }
	}

    
    
    @FXML
    public void baston() {
	
    }
    
    @FXML
    public void chaleco() {
        // Método para capturar pokemon, aún no implementado
    }
    
    @FXML
    public void pesa() {
        // Método para capturar pokemon, aún no implementado
    }
    
    @FXML
    public void eter() {
        // Método para capturar pokemon, aún no implementado
    }
    
    @FXML
    public void pila() {
        // Método para capturar pokemon, aún no implementado
    }
    
    @FXML
    public void pluma() {
        // Método para capturar pokemon, aún no implementado
    }
    

    @FXML
    public void atras(javafx.event.ActionEvent event) {
    	UtilView.mostrarMenuPrincipal(((Node) event.getSource()).getScene());
    }

}
