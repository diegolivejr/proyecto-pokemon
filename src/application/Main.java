package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import modelo.Musica;

public class Main extends Application {
	
	private static Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        try {
        	//Musica musica = new Musica();
        	
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/PantallaLogin.fxml"));
            Parent root = loader.load();
    		Scene scene = new Scene(root);
    		mainStage=primaryStage;
            primaryStage.setScene(scene);
    		primaryStage.setTitle("Proyecto Pokemon");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }		
	
	public static Stage getStage() {
		return mainStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
