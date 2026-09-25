package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import modelo.Entrenador;
import modelo.Pokedex;
import org.junit.jupiter.api.*;

import controller.ControllerCaptura;
import util.UtilView;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ControllerCapturaTest {

    private ControllerCaptura controllerCaptura;
    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    @Test
    void testAtras() {
        // Esta prueba verifica si el método `atras` se ejecuta sin errores
        // Sin embargo, no se puede comprobar la funcionalidad completa sin un entorno JavaFX adecuado
        assertDoesNotThrow(() -> {
            controllerCaptura.atras(new javafx.event.ActionEvent());
        });
    }

    @AfterEach
    void tearDown() {
        controllerCaptura = null;
    }
}
