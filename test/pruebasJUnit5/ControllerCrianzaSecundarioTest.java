package pruebasJUnit5;

import javafx.fxml.FXML;
import javafx.scene.Node;
import modelo.Pokemon;
import util.UtilView;

public class ControllerCrianzaSecundarioTest {

	Pokemon madre;
	Pokemon padre;
	
	@FXML
	public void criarHembra() {
		Pokemon[] parametros=new Pokemon[1];
		UtilView.loadSceneModal("/vistas/PantallaElegirEquipo.fxml", "Elige un Pokémon",parametros);
		madre=parametros[0]; 
		if (madre!=null) {
			if (madre.getSexo()!="H") {
				UtilView.showAlert("Crianza", "El pokémon seleccionado no es Hembra.");
			}
			System.out.println(madre.getNombre());
		}
	}
	
	@FXML
	public void criarMacho() {
		Pokemon[] parametros=new Pokemon[1];
		UtilView.loadSceneModal("/vistas/PantallaElegirEquipo.fxml", "Elige un Pokémon",parametros);
		padre=parametros[0]; 
		if (padre!=null) {
			if (padre.getSexo()!="M") {
				UtilView.showAlert("Crianza", "El pokémon seleccionado no es Macho.");
			}
			System.out.println(padre.getNombre());
		}
	}
	
	@FXML
	public void botonHuevo() {
		
	}

    @FXML
    public void atras(javafx.event.ActionEvent event) {
    	UtilView.mostrarPantalla("../vistas/PantallaCrianza.fxml",((Node) event.getSource()).getScene());
    }

}