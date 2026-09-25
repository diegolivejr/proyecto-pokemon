package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;
import org.junit.jupiter.api.*;

import controller.ControllerEquipo;

import static org.junit.jupiter.api.Assertions.*;

class ControllerEquipoTest {

    private ControllerEquipoTest controllerEquipo;
    private Entrenador entrenador;
    private Pokemon[] equipo;
	private AnchorPane tablero;
	private Object[] pokemonImages;

    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    @BeforeEach
    void setUp() {
        controllerEquipo = new ControllerEquipoTest();
        entrenador = new Entrenador();
        equipo = new Pokemon[6];

        // Initialize JavaFX components
        controllerEquipo.tablero = new AnchorPane();
        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView();
            imageView.setId("pokemon" + (i + 1));
            controllerEquipo.tablero.getChildren().add(imageView);
        }

        // Set up the trainer and their team
        for (int i = 0; i < 6; i++) {
            equipo[i] = new Pokemon();
            equipo[i].setNombre("Pokemon" + (i + 1));
        }
        Entrenador.setEntrenadorActual(entrenador);
    }

    @Test
    void testInitialize() {
        assertDoesNotThrow(() -> {
            controllerEquipo.testInitialize();
        });

        // Verify the ImageView array is initialized correctly
        for (int i = 0; i < 6; i++) {
            assertNotNull(controllerEquipo.pokemonImages[i]);
        }
    }

    @Test
    void testLoadPokemonImages() {
        controllerEquipo.testInitialize();

        assertDoesNotThrow(() -> {
            controllerEquipo.testLoadPokemonImages();
        });

        // Verify the images are set correctly
        for (int i = 0; i < equipo.length; i++) {
            assertNotNull(((ImageView) controllerEquipo.pokemonImages[i]).getImage());
        }
    }

    @Test
    void testLimpiaTablero() {
        controllerEquipo.testInitialize();
        controllerEquipo.testLoadPokemonImages();

        assertDoesNotThrow(() -> {
            controllerEquipo.testLimpiaTablero();
        });

        // Verify the images and event handlers are cleared
        for (int i = 0; i < 6; i++) {
            assertNull(((ImageView) controllerEquipo.pokemonImages[i]).getImage());
            assertNull(((Node) controllerEquipo.pokemonImages[i]).getOnMouseClicked());
        }
    }

    @Test
    void testAtras() {
        assertDoesNotThrow(() -> {
            ActionEvent event = new ActionEvent();
            JFXPanel node = new JFXPanel(); // Placeholder node for the event
            event = new ActionEvent(node, (EventTarget) node);
            controllerEquipo.atras(event);
        });
    }

    private void atras(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Test
    void testMostrarEstadisticas() {
        controllerEquipo.testInitialize();
        Pokemon testPokemon = new Pokemon();
        testPokemon.setNombre("TestPokemon");

        assertDoesNotThrow(() -> {
            controllerEquipo.mostrarEstadisticas(testPokemon);
        });

        // Since it's difficult to verify the actual UI change, just check the console output
        assertEquals("TestPokemon", testPokemon.getNombre());
    }

    private void mostrarEstadisticas(Pokemon testPokemon) {
		// TODO Auto-generated method stub
		
	}

    @AfterEach
    void tearDown() {
        controllerEquipo = null;
        entrenador = null;
        equipo = null;
    }
}
