package modelo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Musica {
    private MediaPlayer mediaPlayer;

    public Musica() {
        try {
            // Convierte la ruta relativa a una URI absoluta
            File musicFile = new File("C:\\Users\\jorge\\Documents\\GitHub\\proyecto_pokemon\\src\\sonidos\\coral_chorus.mp3");
            String uri = musicFile.toURI().toString();
            
            // Crea un objeto Media con la URI
            Media media = new Media(uri);
            
            // Crea el MediaPlayer con el objeto Media
            mediaPlayer = new MediaPlayer(media);
            
            // Reproduce la música
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo de música: " + e.getMessage());
        }
    }
}
