package controller;

import java.io.IOException;
import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Entrenador;
import util.UtilView;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Este controlador permite curar al equipo de Pokémon y manejar otras interacciones como el acceso al PC de almacenamiento.
 *
 * @version 1.0
 * @author JorgeDiego
 * @see modelo.Entrenador
 */

public class ControllerCentroPokemon {
	
	/**
	 * Cura a todos los Pokémon del equipo actual del entrenador.
	 *
	 * @version 1.0
	 * @author JorgeDiego
	 * @see modelo.Entrenador#curarEquipo()
	 */
	
	@FXML
    public void curar() {
		Entrenador.getEntrenadorActual().curarEquipo();
        UtilView.showInfo("Centro Pokémon", "Se han curado todos los pokemons de tu equipo.");
    }
	
    @FXML
    public void pc(ActionEvent event) {
        try {
            // Cargar la vista para el PC de almacenamiento Pokémon
            Parent pcView = FXMLLoader.load(getClass().getResource("../vistas/PantallaPC.fxml"));
            
            // Obtener el escenario actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(pcView));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla del PC: " + e.getMessage());
        }
    	
    }
    
    @FXML
    public void recuerdaMovimientos(ActionEvent event) {
        try {
            Parent menuView = FXMLLoader.load(getClass().getResource("/vistas/PantallaRecuerdaMovimientos.fxml"));
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(menuView));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla del menú: " + e.getMessage());
        }
    }

    
    @FXML
    public void atras(javafx.event.ActionEvent event) {
    	UtilView.mostrarMenuPrincipal(((Node) event.getSource()).getScene());
    }
}
