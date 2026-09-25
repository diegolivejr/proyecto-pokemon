package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import modelo.Entrenador;
import modelo.Movimiento;
import modelo.Pokemon;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerRecuerdaMovimientosTest {

    private ControllerRecuerdaMovimientosTest controllerRecuerdaMovimientos;
    private Pokemon pokemon;
    private Movimiento movimiento;
	private ImageView imagen;
	private HBox boxActivos;
	private FlowPane boxAprendidos;

    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Inicializa el entorno de JavaFX
    }

    @BeforeEach
    void setUp() {
        controllerRecuerdaMovimientos = new ControllerRecuerdaMovimientosTest();
        pokemon = new Pokemon();
        movimiento = new Movimiento(0, null, null, null, null, null, null, null, null, 0);

        // Inicializa los componentes de JavaFX
        controllerRecuerdaMovimientos.imagen = new ImageView();
        controllerRecuerdaMovimientos.boxActivos = new HBox();
        controllerRecuerdaMovimientos.boxAprendidos = new FlowPane();

        // Configura el Pokémon y sus movimientos
        movimiento.setNomMovimiento("Impactrueno");
        movimiento.setIdMovimiento(1);
        pokemon.setNombre("Pikachu");
    }

    @Test
    void testEquipo() {
        assertDoesNotThrow(() -> {
            ActionEvent event = new ActionEvent();
            controllerRecuerdaMovimientos.equipo(event);
        });
    }

    private void equipo(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Test
    void testRefrescaVista() {
        controllerRecuerdaMovimientos.pokemon = pokemon;

        assertDoesNotThrow(() -> {
            controllerRecuerdaMovimientos.testRefrescaVista();
        });

        // Verifica que la imagen del Pokémon se haya actualizado
        assertNotNull(controllerRecuerdaMovimientos.imagen.getImage());

        // Verifica que los botones de movimientos activos se hayan creado
        assertEquals(1, controllerRecuerdaMovimientos.boxActivos.getChildren().size());

        // Verifica que los botones de movimientos aprendidos se hayan creado
        assertEquals(1, controllerRecuerdaMovimientos.boxAprendidos.getChildren().size());
    }

    @Test
    void testActivaDesactiva() {
        controllerRecuerdaMovimientos.pokemon = pokemon;

        assertDoesNotThrow(() -> {
            controllerRecuerdaMovimientos.activaDesactiva(movimiento);
        });
    }

    private void activaDesactiva(Movimiento movimiento2) {
		// TODO Auto-generated method stub
		
	}
    
    @Test
    void testAtras() {
        assertDoesNotThrow(() -> {
            ActionEvent event = new ActionEvent();
            controllerRecuerdaMovimientos.atras(event);
        });
    }

    private void atras(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@AfterEach
    void tearDown() {
        controllerRecuerdaMovimientos = null;
        pokemon = null;
        movimiento = null;
    }
}
