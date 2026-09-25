package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerElegirEquipoTest {

    private ControllerElegirEquipoTest controllerElegirEquipo;
    private Pokemon[] lista;
    private Pokemon[] equipo;
	private AnchorPane selector;

    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    @BeforeEach
    void setUp() {
        controllerElegirEquipo = new ControllerElegirEquipoTest();
        lista = new Pokemon[1];
        equipo = new Pokemon[3];

        // Initialize JavaFX components
        controllerElegirEquipo.selector = new AnchorPane();
    }

    @Test
    void testInitialize() {
        assertDoesNotThrow(() -> {
            controllerElegirEquipo.testInitialize();
        });
    }

    @Test
    void testMostrarEquipo() {
        // Mock some ImageViews in the selector
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView();
            controllerElegirEquipo.selector.getChildren().add(imageView);
        }

        assertDoesNotThrow(() -> {
            controllerElegirEquipo.testMostrarEquipo();
        });

        // Verify that the ImageViews have been set with images
        for (Node node : controllerElegirEquipo.selector.getChildren()) {
            ImageView imageView = (ImageView) node;
            assertNotNull(imageView.getImage());
        }
    }

    @AfterEach
    void tearDown() {
        controllerElegirEquipo = null;
        lista = null;
        equipo = null;
    }
}
