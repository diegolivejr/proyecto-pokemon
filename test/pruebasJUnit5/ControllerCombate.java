package pruebasJUnit5;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Combate;
import modelo.Entrenador;
import modelo.Movimiento;
import modelo.Pokemon;
import util.UtilView;



public class ControllerCombate {

	Combate combate;
	
	@FXML ImageView tupokemon;
	@FXML ImageView pokemonenemigo;
	@FXML AnchorPane selector;		
	@FXML AnchorPane botonera;
	@FXML ProgressBar vidatupokemon;	
	@FXML ProgressBar vidaenemigo;
	@FXML Label nombreTu;
	@FXML Label nombreRival;
	
	
	
    public void initialize() {
    	combate = new Combate(Entrenador.getEntrenadorActual());
    	refrescaEntrenador();
    	refrescaEnemigo();
    }
    
	
    private void refrescaEntrenador() {
    	tupokemon.setImage(UtilView.getImagenDetras(combate.getPaladin().getNombre()));
    	nombreTu.setText(combate.getPaladin().getMote());
    	vidatupokemon.setProgress(combate.getPorcentajeSaludEntrenador());
    }

    
    private void refrescaEnemigo() {
    	pokemonenemigo.setImage(UtilView.getImagenDelante(combate.getRival().getNombre()));
    	vidaenemigo.setProgress(combate.getPorcentajeSaludRival());
    	nombreRival.setText(combate.getRival().getNombre());    	
    }

    
	@FXML
	public void mostrarEquipo() {
		selector.toFront();
		selector.setVisible(true);
		int index = 0;
		for (Node nodo : selector.getChildren()) {
        	String nodoId = nodo.getId();
        	if (nodoId == null) { // es hueco para mostrar un pokemon
        		ImageView imageView = ((ImageView)nodo);
        		Pokemon equipo[] = combate.getEntrenador().getEquipo();
        		imageView.setImage(null);
        		imageView.setOnMouseClicked(null);
        		if (index <= equipo.length -1) {
        			Pokemon pokemon = equipo[index];
        			Image image;
    				image = UtilView.getImagenDelante(pokemon.getNombre());
        			if (pokemon.getSalud()>=0) { // está vivo
	        			imageView.setImage(image);
	        			imageView.setOnMouseClicked(new ManejaMousePokemon(index));
        			}
        		}        			
        		index++;
        	}			
		} 		
	}
		
	public void setPaladin(int index) {
		Pokemon equipo[] = combate.getEntrenador().getEquipo();
		if (index >= equipo.length) return;
		Pokemon nuevoPaladin = equipo[index];
		if (nuevoPaladin.getSalud()<=0) {
			UtilView.showError("Combate", "Tu pokémon "+nuevoPaladin.getMote()+" está muerto.\nNo lo puedes volver a utilizar.");
		}
    	combate.setPaladin(nuevoPaladin);
    	refrescaEntrenador();
    	//TODO: Hacer imagen más grande    	
	}
	
	public void eligeNuevoPaladin(int index) {		
		setPaladin(index);
		selector.toBack();
		selector.setVisible(false);
	}
        
    @FXML
    public void pokemon() {
    	
    }
    @FXML
    public void mochila() {
    	
    }
    
    @FXML
    public void estadisticas() {
   		UtilView.loadSceneModal("/vistas/PantallaEstadisticas.fxml","Estadísticas",combate.getPaladin());
    }
    
 
	
    @FXML
    public void atras(javafx.event.ActionEvent event) {
    	UtilView.mostrarMenuPrincipal(((Node) event.getSource()).getScene());
    }
    
    // Maneja los clicks en los ImageViews
    class ManejaMousePokemon implements EventHandler<MouseEvent> {
    	
    	int index = -1;    	
    	
    	public ManejaMousePokemon(int index) {
			this.index = index;
		}

        @Override
        public void handle(MouseEvent event) {
            System.out.println("Se ha pulsado el pokemon "+index);
            eligeNuevoPaladin(index);
            event.consume();
        }
    };        			
    
    // Maneja los clicks en los botones de movimientos de lucha
    class ManejaMovimientoLucha implements EventHandler<ActionEvent> {
    	
    	Movimiento movimiento;    	
    	
    	public ManejaMovimientoLucha(Movimiento movimiento) {
			this.movimiento = movimiento;
		}

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    }
}

