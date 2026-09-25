package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import modelo.Entrenador;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerMenuTest {

    private ControllerMenuTest controllerMenu;
    private Entrenador entrenador;
	private BorderPane mainContainer;
	private Label txtDinero;

    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    @BeforeEach
    void setUp() {
        controllerMenu = new ControllerMenuTest();
        entrenador = new Entrenador();

        // Initialize JavaFX components
        controllerMenu.mainContainer = new BorderPane();
        controllerMenu.txtDinero = new Label();

        // Set up the trainer's money
        entrenador.setDinero(1000);
        Entrenador.setEntrenadorActual(entrenador);
    }

    @Test
    void testInitialize() {
        assertDoesNotThrow(() -> {
            controllerMenu.testInitialize();
        });

        // Verify the money label is set correctly
        assertEquals("PokeDólares: 1,000", controllerMenu.txtDinero.getText());
    }

    @Test
    void testCaptura() {
        assertDoesNotThrow(() -> {
            controllerMenu.testCaptura();
        });
    }

    @Test
    void testEquipo() {
        assertDoesNotThrow(() -> {
            controllerMenu.testEquipo();
        });

        // Verify that the main container is updated
        assertNotNull(controllerMenu.mainContainer.getCenter());
    }

    @Test
    void testCentropokemon() {
        assertDoesNotThrow(() -> {
            controllerMenu.testCentropokemon();
        });
    }

    @Test
    void testCombate() {
        assertDoesNotThrow(() -> {
            controllerMenu.testCombate();
        });
    }

    @Test
    void testEntrenamiento() {
        assertDoesNotThrow(() -> {
            controllerMenu.testEntrenamiento();
        });
    }

    @Test
    void testCrianza() {
        assertDoesNotThrow(() -> {
            controllerMenu.testCrianza();
        });
    }

    @Test
    void testMochila() {
        assertDoesNotThrow(() -> {
            controllerMenu.testMochila();
        });
    }

    @AfterEach
    void tearDown() {
        controllerMenu = null;
        entrenador = null;
    }
}
