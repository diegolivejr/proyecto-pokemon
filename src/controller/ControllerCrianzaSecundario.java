package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import modelo.Entrenador;
import modelo.Pokemon;
import util.UtilView;

public class ControllerCrianzaSecundario {

	Pokemon madre;
	Pokemon padre;
	
	@FXML ImageView imgHembra;
	@FXML ImageView imgMacho;
	
	@FXML
	public void criarHembra() {
		Pokemon[] parametros=new Pokemon[1];
		UtilView.loadSceneModal("/vistas/PantallaElegirEquipo.fxml", "Elige un Pokémon",parametros);
		if (parametros[0]!=null) {
			if (!parametros[0].getSexo().equals("H")) {
				UtilView.showAlert("Crianza", "El pokémon seleccionado no es Hembra.");
			} else {
				madre=parametros[0];		
				imgHembra.setImage(UtilView.getImagenDelante(madre.getNombre()));
				System.out.println("Madre: "+madre.getMote());
				efecto(imgHembra);
			}
		}
	}
	
	@FXML
	public void criarMacho() {
		Pokemon[] parametros=new Pokemon[1];
		UtilView.loadSceneModal("/vistas/PantallaElegirEquipo.fxml", "Elige un Pokémon",parametros);		 
		if (parametros[0]!=null) {
			if (!parametros[0].getSexo().equals("M")) {
				UtilView.showAlert("Crianza", "El pokémon seleccionado no es Macho.");
			} else {
				padre=parametros[0];
				imgMacho.setImage(UtilView.getImagenDelante(padre.getNombre()));
				System.out.println("Padre: "+padre.getMote());				
				efecto(imgMacho);
			}
		}
	}
	
	
	@FXML
	public void botonHuevo() {
		if (madre==null || padre==null) return;
		String mote = UtilView.getText("Nombre del hijo/a:",madre.getNombre()+" cria");
		Pokemon hijo = Entrenador.getEntrenadorActual().criar(padre, madre, mote);
		if (hijo==null) {
			UtilView.showError("Crianza", "No se ha podido criar el pokémon.");
		} else {
			UtilView.showInfo("Crianza", "Has criado un nuevo "+hijo.getNombre());
		}
		UtilView.mostrarPantalla("../vistas/PantallaCrianza.fxml");
	}
	
	private void efecto(ImageView imageView) {
		DropShadow effect = new DropShadow(); 
		effect.setBlurType(BlurType.GAUSSIAN); 
		effect.setColor(Color.BLACK); 
		effect.setHeight(5); 
		effect.setWidth(5); 
		effect.setRadius(5);  
		effect.setOffsetX(0); 
		effect.setOffsetY(0); 
		effect.setSpread(10);  		
		imageView.setEffect(effect);      
   	}
	

    @FXML
    public void atras(javafx.event.ActionEvent event) {
    	UtilView.mostrarPantalla("../vistas/PantallaCrianza.fxml",((Node) event.getSource()).getScene());
    }

}
