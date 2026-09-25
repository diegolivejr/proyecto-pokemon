package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Entrenador;
import org.junit.jupiter.api.*;

import controller.ControllerCentroPokemon;
import util.UtilView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerCentroPokemonTest {

    private ControllerCentroPokemon controllerCentroPokemon;

    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    @BeforeEach
    void setUp() {
        controllerCentroPokemon = new ControllerCentroPokemon();
    }

    @Test
    void testCurar() {
        Entrenador entrenador = new Entrenador();
        Entrenador.setEntrenadorActual(entrenador);

        // Simula la acción de curar al equipo
        controllerCentroPokemon.curar();

        // Verifica que los Pokémon han sido curados
        assertTrue(entrenador.equipoCurado());
    }

    @Test
    void testPc() {
        assertDoesNotThrow(() -> {
            ActionEvent event = new ActionEvent();
            JFXPanel node = new JFXPanel(); // Placeholder node for the event
            event = new ActionEvent();
            controllerCentroPokemon.pc(event);
        });
    }

    @Test
    void testRecuerdaMovimientos() {
        assertDoesNotThrow(() -> {
            ActionEvent event = new ActionEvent();
            JFXPanel node = new JFXPanel(); // Placeholder node for the event
            event = new ActionEvent();
            controllerCentroPokemon.recuerdaMovimientos(event);
        });
    }

    @Test
    void testAtras() {
        assertDoesNotThrow(() -> {
            ActionEvent event = new ActionEvent();
            JFXPanel node = new JFXPanel(); // Placeholder node for the event
            event = new ActionEvent();
            controllerCentroPokemon.atras(event);
        });
    }

    @AfterEach
    void tearDown() {
        controllerCentroPokemon = null;
    }
}