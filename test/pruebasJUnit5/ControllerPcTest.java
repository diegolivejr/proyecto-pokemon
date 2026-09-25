package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;
import org.junit.jupiter.api.*;

import controller.ControllerPc;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPcTest {

    private ControllerPcTest controllerPc;
    private Entrenador entrenador;
    private Pokemon[] pc;
	private AnchorPane tablero;
	private Object[] pokemonImages;

    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Inicializa el entorno de JavaFX
    }

    @BeforeEach
    void setUp() {
        controllerPc = new ControllerPcTest();
        entrenador = new Entrenador();
        pc = new Pokemon[30];

        // Inicializa los componentes de JavaFX
        controllerPc.tablero = new AnchorPane();
        for (int i = 0; i < 30; i++) {
            ImageView imageView = new ImageView();
            imageView.setId("pokemon" + (i + 1));
            controllerPc.tablero.getChildren().add(imageView);
        }

        // Configura el PC del entrenador
        for (int i = 0; i < 30; i++) {
            pc[i] = new Pokemon();
            pc[i].setNombre("Pokemon" + (i + 1));
        }
        Entrenador.setEntrenadorActual(entrenador);
    }

    @Test
    void testInitialize() {
        assertDoesNotThrow(() -> {
            controllerPc.testInitialize();
        });

        // Verifica que el arreglo de ImageViews se inicialice correctamente
        for (int i = 0; i < 30; i++) {
            assertNotNull(controllerPc.pokemonImages[i]);
        }
    }

    @Test
    void testLoadPokemonImages() {
        controllerPc.testInitialize();

        assertDoesNotThrow(() -> {
            controllerPc.testLoadPokemonImages();
        });

        // Verifica que las imágenes se hayan configurado correctamente
        for (int i = 0; i < pc.length; i++) {
            assertNotNull(((ImageView) controllerPc.pokemonImages[i]).getImage());
        }
    }

    @Test
    void testLimpiaTablero() {
        controllerPc.testInitialize();
        controllerPc.testLoadPokemonImages();

        assertDoesNotThrow(() -> {
            controllerPc.testLimpiaTablero();
        });

        // Verifica que las imágenes y los controladores de eventos se hayan limpiado
        for (int i = 0; i < 30; i++) {
            assertNull(((ImageView) controllerPc.pokemonImages[i]).getImage());
            assertNull(((Node) controllerPc.pokemonImages[i]).getOnMouseClicked());
        }
    }

    @Test
    void testAddEquipo() {
        assertDoesNotThrow(() -> {
            controllerPc.testAddEquipo();
        });
    }

    @Test
    void testAtras() {
        assertDoesNotThrow(() -> {
            // Simula el evento de acción de retroceder
            javafx.event.ActionEvent event = new javafx.event.ActionEvent();
            controllerPc.atras(event);
        });
    }

    private void atras(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Test
    void testEstadisticas1() {
        assertDoesNotThrow(() -> {
            MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 1, false, false, false, false, true, false, false, false, false, false, null);
            controllerPc.estadisticas1(event);
        });
    }

    private void estadisticas1(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Test
    void testEstadisticas2() {
        assertDoesNotThrow(() -> {
            MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 1, false, false, false, false, true, false, false, false, false, false, null);
            controllerPc.estadisticas2(event);
        });
    }

    private void estadisticas2(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}


    @AfterEach
    void tearDown() {
        controllerPc = null;
        entrenador = null;
        pc = null;
    }
}
