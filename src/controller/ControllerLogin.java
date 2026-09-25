package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Entrenador;
import util.UtilView;
import bbd.BD;
import bbd.LoginBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLogin {

    @FXML
    private PasswordField passw;

    @FXML
    private TextField user1;
    
    private LoginBD loginBD = new LoginBD();
    

    @FXML
    void iniciar() {
        String username = user1.getText();
        String password = passw.getCharacters().toString();

        boolean credentialsCorrect = loginBD.checkCredentials(username, password);

        if (credentialsCorrect) {
            loadNextScene();
        } else {
            UtilView.showError("Credenciales incorrectas", "El nombre de entrenador o la contraseña son incorrectos.");
        }
    }


    private void loadNextScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/PantallaMenuPrincipal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) user1.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registrar() {
        String newUsername = user1.getText();
        String newPassword = passw.getCharacters().toString();

        boolean registrationSuccessful = loginBD.registerUser(newUsername, newPassword);

        if (registrationSuccessful) {
            UtilView.showInfo("Registro exitoso", "Usuario registrado correctamente.");
        } else {
            UtilView.showError("Error en el registro", "No se te pudo registrar como usuario.\nInténtalo de nuevo más tarde.");
        }
    }

}
