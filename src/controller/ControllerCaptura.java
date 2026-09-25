package controller;

import javafx.fxml.FXML;
import java.util.Random;
import bbd.BD;
import bbd.PokedexBD;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokedex;
import modelo.Pokemon;
import util.UtilView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

/**
 * Controlador para la gestión de capturas de Pokémon.
 * Permite cambiar al Pokémon actualmente visible por un Pokémon aleatorio,
 * para capturarlo. Utiliza la base de datos para obtener información
 * del Pokémon aleatorio y actualizar la vista.
 *
 * @version 1.0
 * @author JorgeDiego
 * @see modelo.Pokedex
 * @see bbd.PokedexBD
 */

public class ControllerCaptura {
    @FXML
	public ImageView pokemonrandom;
    public Pokedex pokedex = null;


    /**
     * Cambia el Pokémon visible actual por un Pokémon aleatorio obtenido de la base de datos.
     * Muestra en la vista el nombre y otros detalles del Pokémon encontrado.
     * Lanza una alerta si ocurre algún error durante la obtención del Pokémon desde la base de datos.
     *
     * @throws SQLException si hay un error de conexión o consulta a la base de datos
     * @version 1.0
     * @author JorgeDiego
     * @see Pokedex#getRadomPokemon()
     */
    @FXML
    public void cambiar() {   	
        try {
        	pokedex = this.getRadomPokemon();
        	System.out.println("Pokemon encontado: "+pokedex.getId() + " - "+ pokedex.getNombre());
        	String randomImage=pokedex.getNombre().toLowerCase()+"_delante.png";

            // Actualizar la imagen en la interfaz
            pokemonrandom.setImage(new Image("file:src/imagenes/pokemon_delante/" + randomImage));
        } catch (Exception e) {
            System.out.println("Error accessing image files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void capturar() {
    	if (pokedex == null) return;
        Random rand = new Random();
        boolean capturado = ((rand.nextInt(100)+1) <= 60); // 60 %
        
        if (capturado) {
        	String mote = UtilView.getText("Mote del pokémon:", pokedex.getNombre());
        	Entrenador.getEntrenadorActual().capturar(pokedex,mote);
        	if (capturado)
        		UtilView.showInfo("¡Captura exitosa!", "Has capturado exitosamente al Pokémon " + pokedex.getNombre() + " y ha sido guardado en la base de datos.");
        	else
                UtilView.showError("Captura fallida", "No se ha podido guardar el pokémon, ¿demasiados pokémons?.");
        	
        } else {
        	UtilView.showError("Captura fallida", "El Pokémon ha escapado.");
            // Hacer que el Pokémon 'desaparezca' al escapar
        }
        pokedex = null;
        pokemonrandom.setImage(null);  // Esto borrará la imagen actual mostrada
     
    }


    @FXML
    public void atras(javafx.event.ActionEvent event) {
    	UtilView.mostrarMenuPrincipal(((Node) event.getSource()).getScene());
    }
    
    private Pokedex getRadomPokemon() {
    	return Entrenador.getEntrenadorActual().getRandomPokedex();
    }
}

