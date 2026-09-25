package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import modelo.Entrenador;
import modelo.Objeto;
import org.junit.jupiter.api.*;

import controller.ControllerMochila;

import static org.junit.jupiter.api.Assertions.*;

class ControllerMochilaTest {

    private ControllerMochila controllerMochila;
    private Entrenador entrenador;

    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Inicializa el entorno de JavaFX
    }

    @BeforeEach
    void setUp() {
        controllerMochila = new ControllerMochila();
        entrenador = new Entrenador();

        // Configura el dinero del entrenador
        entrenador.setDinero(5000);
        Entrenador.setEntrenadorActual(entrenador);
    }

    @Test
    void testAnillo() {
        assertDoesNotThrow(() -> {
            controllerMochila.anillo();
        });

        // Verifica que el dinero del entrenador se haya actualizado correctamente
        assertEquals(500, entrenador.getDinero(), "El dinero no se actualizó correctamente después de la compra del anillo.");
    }

    @Test
    void testFondosInsuficientes() {
        entrenador.setDinero(1000); // Configura dinero insuficiente para la compra
        assertDoesNotThrow(() -> {
            controllerMochila.anillo();
        });

        // Verifica que el dinero del entrenador no se haya actualizado debido a fondos insuficientes
        assertEquals(1000, entrenador.getDinero(), "El dinero no debería haberse actualizado debido a fondos insuficientes.");
    }

    @Test
    void testBaston() {
        assertDoesNotThrow(() -> {
            controllerMochila.baston();
        });
    }

    @Test
    void testChaleco() {
        assertDoesNotThrow(() -> {
            controllerMochila.chaleco();
        });
    }

    @Test
    void testPesa() {
        assertDoesNotThrow(() -> {
            controllerMochila.pesa();
        });
    }

    @Test
    void testEter() {
        assertDoesNotThrow(() -> {
            controllerMochila.eter();
        });
    }

    @Test
    void testPila() {
        assertDoesNotThrow(() -> {
            controllerMochila.pila();
        });
    }

    @Test
    void testPluma() {
        assertDoesNotThrow(() -> {
            controllerMochila.pluma();
        });
    }

    @Test
    void testAtras() {
        assertDoesNotThrow(() -> {
            // Simula el evento de acción de retroceder
            javafx.event.ActionEvent event = new javafx.event.ActionEvent();
            controllerMochila.atras(event);
        });
    }

    @AfterEach
    void tearDown() {
        controllerMochila = null;
        entrenador = null;
    }
}
