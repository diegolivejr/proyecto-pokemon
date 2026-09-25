package controller;
 
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
 
import bbd.BD;
import bbd.PokemonBD;
import controller.ControllerCombate.ManejaMousePokemon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;
import util.UtilView;

 
public class ControllerPc {
	
	@FXML
	private AnchorPane tablero;

	
    private ImageView[] pokemonImages = new ImageView[30];
 
    private PokemonBD pokemon = new PokemonBD();
    
    
    public void initialize() {
    	if (tablero==null) return;
        tablero.getChildren().forEach((nodo) -> {
        	String nodoId = nodo.getId();
        	if (nodoId!=null && nodoId.startsWith("pokemon")) {
        		int numero =  Integer.parseInt(nodoId.replaceAll("pokemon", ""));
        		pokemonImages[numero-1] = (ImageView)nodo;        		
        	}
        });
        loadPokemonImages();
    }
 
    private void loadPokemonImages() {
    	limpiaTablero();
        try {
        	Pokemon pc[] = Entrenador.getEntrenadorActual().getPC();
        	for (int index=0; index < pc.length; index++) {
        		Image image = UtilView.getImagenDelante(pc[index].getNombre());
            	pokemonImages[index].setImage(image);
        		pokemonImages[index].setOnMouseClicked(new ManejaMousePokemon(pc[index]));
        	}
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading Pokémon images: " + e.getMessage());
        }
    }
    
    private void limpiaTablero() {
    	for (int i=0; i<pokemonImages.length; i++) {
    		pokemonImages[i].setImage(null);
    		pokemonImages[i].setOnMouseClicked(null);
    	}
    }
    
    public class StateManager {
        public static boolean isEquipoFull = false;
    }
        public void update() {
            if (StateManager.isEquipoFull) {
                // Código para "llenar" el PC
            }
        }
        
        @FXML
        public void estadisticas1(MouseEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/PantallaEstadisticas.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void estadisticas2(MouseEvent event) {
            try {
                Parent secondaryView = FXMLLoader.load(getClass().getResource("/vistas/PantallaEstadisticas.fxml"));
                Scene secondaryScene = new Scene(secondaryView);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(secondaryScene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
    

    @FXML 
    public void atras(javafx.event.ActionEvent event) {
    	UtilView.mostrarMenuPrincipal(((Node) event.getSource()).getScene());
    }

	public void mostrarEstadisticas(Pokemon pokemon) {
		UtilView.loadSceneModal("/vistas/PantallaEstadisticas.fxml","Estadísticas",pokemon);
    	loadPokemonImages();    			
	}        
    
    
    // Maneja los clicks en los ImageViews
    class ManejaMousePokemon implements EventHandler<MouseEvent> {    	
    	
    	Pokemon pokemon = null;    	    	
    	
    	public ManejaMousePokemon(Pokemon pokemon) {
			this.pokemon = pokemon;
		}

    	@Override
        public void handle(MouseEvent event) {
        	if (pokemon == null) return;
            System.out.println("Se ha pulsado el pokemon "+pokemon.getNombre());
            mostrarEstadisticas(pokemon);
            event.consume();
        }
    };        			
    
}
 
