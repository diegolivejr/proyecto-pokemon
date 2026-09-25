package pruebasJUnit5;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import modelo.Entrenador;
import modelo.Pokedex;
import modelo.Pokemon;
import org.junit.jupiter.api.*;
import util.UtilView;

import static org.junit.jupiter.api.Assertions.*;

class ControllerEntrenamientoTest {

    private ControllerEntrenamientoTest controllerEntrenamiento;
    private Pokemon pokemon;
    private Pokedex rival;
    private Entrenador entrenador;
private ImageView pokemonEquipo;
private ImageView pokemonRival;

    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    @Test
    void testInitialize() {
        assertDoesNotThrow(() -> {
            controllerEntrenamiento.testInitialize();
        });

        assertNotNull(controllerEntrenamiento.pokemon);
        assertEquals("Pikachu", controllerEntrenamiento.pokemon.getNombre());
    }

    @Test
    void testRefrescaVista() {
        // Set up the Pokémon and rival
        controllerEntrenamiento.pokemon = pokemon;
        controllerEntrenamiento.rival = rival;

        assertDoesNotThrow(() -> {
            controllerEntrenamiento.testRefrescaVista();
        });

        // Verify the images are set correctly
        assertNotNull(controllerEntrenamiento.pokemonEquipo.getImage());
        assertNotNull(controllerEntrenamiento.pokemonRival.getImage());
    }

    @Test
    void testEntrenar() {
        controllerEntrenamiento.pokemon = pokemon;
        controllerEntrenamiento.rival = rival;

        assertDoesNotThrow(() -> {
            controllerEntrenamiento.testEntrenar();
        });

        // Verify the Pokémon's experience has increased
        assertTrue(controllerEntrenamiento.pokemon.getExperiencia() > 100);
        assertNull(controllerEntrenamiento.rival);
    }

    @Test
    void testCambiarPokemon() {
        assertDoesNotThrow(() -> {
            controllerEntrenamiento.testCambiarPokemon();
        });

        // Verify the rival Pokémon is set
        assertNotNull(controllerEntrenamiento.rival);
    }
    
};