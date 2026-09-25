package util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Optional;

import application.Main;
import controller.ControllerWithAttributes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Entrenador;


public class UtilView {

	// TODO: Reutilizar código no repetir
	
	public static Image getImagenDelante(String nombre) {
	    String fullPath = "/imagenes/pokemon_delante/" + nombre.toLowerCase() + "_delante.png";
	    //System.out.println("Cargando imagen desde: " + fullPath);
	    InputStream is = Entrenador.getEntrenadorActual().getClass().getResourceAsStream(fullPath);
	    if (is == null) {
	        System.out.println("No se pudo encontrar la imagen: " + fullPath);
	        return null;
	    }
		Image image = new Image(is);
	    return image;		
	}

	
	public static Image getImagenDetras(String nombre) {
	    String fullPath = "/imagenes/pokemon_detras/" + nombre.toLowerCase() + "_detras.png";
	    //System.out.println("Cargando imagen desde: " + fullPath);
	    InputStream is = Entrenador.getEntrenadorActual().getClass().getResourceAsStream(fullPath);
	    if (is == null) {
	        System.out.println("No se pudo encontrar la imagen: " + fullPath);
	        return null;
	    }
		Image image = new Image(is);
	    return image;		
	}
	
    public static void loadSceneInPanel(String fxml, Pane pane ) {
        try {
            FXMLLoader loader = new FXMLLoader(Entrenador.getEntrenadorActual().getClass().getResource(fxml));
            Parent newRoot = loader.load();
            pane.getChildren().clear(); 
            pane.getChildren().add(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadSceneModal(String fxml, String title, Object attributes) {
        try {
        	FXMLLoader fxmlLoader = new FXMLLoader(Entrenador.getEntrenadorActual().getClass().getResource(fxml));
            Parent pcView = fxmlLoader.load();
            ControllerWithAttributes controller = (ControllerWithAttributes)fxmlLoader.getController();            
            controller.initializeAttributes(attributes);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pcView));
    		stage.setTitle(title);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla: " + e.getMessage());
        }    	
    }

    private static void internalAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public static void showInfo(String title, String content) {
    	internalAlert(Alert.AlertType.INFORMATION, title, content);
    }

    public static void showAlert(String title, String content) {
    	internalAlert(Alert.AlertType.WARNING, title, content);
    }
    
    public static void showError(String title, String content) {
    	internalAlert(Alert.AlertType.ERROR, title, content);
    }
    
    public static boolean confirmAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
            return true;
        }        
        return false;
    }
    
    public static String getText(String title, String defaultValue) {
    	TextInputDialog td = new TextInputDialog(defaultValue);
    	td.setTitle(title);
    	td.setHeaderText("");
        td.showAndWait(); 
        return td.getEditor().getText();         		
    }
    
    
    
    public static String formateaDinero(int dinero) {
    	DecimalFormat formatter = new DecimalFormat("#,##0");
    	return formatter.format(dinero);    	    	
    }

    
    public static void mostrarMenuPrincipal(Scene scene) {
    	mostrarPantalla("/vistas/PantallaMenuPrincipal.fxml",scene);
    }

    public static void mostrarMenuPrincipal() {
    	mostrarPantalla("/vistas/PantallaMenuPrincipal.fxml",Main.getStage());
    }
       
    
    public static void mostrarPantalla(String ruta, Scene scene) {
        try {
            // Cargar la vista del menú
            Parent menuView = FXMLLoader.load(Entrenador.getEntrenadorActual().getClass().getResource(ruta));
            
            // Obtener el escenario actual y establecer la nueva escena
            Stage stage = (Stage)scene.getWindow();
            stage.setScene(new Scene(menuView));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla del menú: " + e.getMessage());
        }
    	
    }
    

    public static void mostrarPantalla(String ruta, Stage stage) {
        try {
            // Cargar la vista del menú
            Parent menuView = FXMLLoader.load(Entrenador.getEntrenadorActual().getClass().getResource(ruta));
            
            // Obtener el escenario actual y establecer la nueva escena
            stage.setScene(new Scene(menuView));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla del menú: " + e.getMessage());
        }
    	
    }

	
    public static void mostrarPantalla(String ruta) {
        try {
            // Cargar la vista del menú
            Parent menuView = FXMLLoader.load(Entrenador.getEntrenadorActual().getClass().getResource(ruta));
            
            // Obtener el escenario actual y establecer la nueva escena
            Main.getStage().setScene(new Scene(menuView));
            Main.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla del menú: " + e.getMessage());
        }
    	
    }
	
	
}
