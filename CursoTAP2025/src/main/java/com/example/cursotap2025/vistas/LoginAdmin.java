package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.AdminDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginAdmin  extends Stage {

    private TextField username;
    private PasswordField password;
    private Button login;
    private Label lblLogin, lblUser, lblPassword;
    private Scene scene;

    public LoginAdmin() {
        CrearUI();
        this.setTitle("Login Administrador");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        lblLogin = new Label("Bienvenido Administrador");
        lblLogin.getStyleClass().add("label-title");

        lblUser = new Label("Usuario");
        lblUser.getStyleClass().add("label");

        lblPassword = new Label("Ingrese la contrasena");
        lblPassword.getStyleClass().add("label");

        username = new TextField();
        username.setPromptText("Ingrese su usuario");
        username.getStyleClass().add("text-field");

        password = new PasswordField();
        password.setPromptText("Ingrese contraseña");
        password.getStyleClass().add("password-field");

        login = new Button("Iniciar Sesion");
        login.getStyleClass().add("button");
        login.setOnAction(e -> validarCredenciales());

        VBox layout = new VBox(10, lblLogin, lblUser, username, lblPassword, password, login);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        scene = new Scene(layout, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/Styles/login.css").toExternalForm());
    }

    private void validarCredenciales() {
        String user = username.getText().trim();
        String pass = password.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        try {
            AdminDAO dao = new AdminDAO();
            if (dao.validarAdmin(user, pass)) {
                //mostrarAlerta(Alert.AlertType.INFORMATION, "Login exitoso", "Bienvenido, " + user + "!");
                new OpcionesAdmin();
                this.close();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Credenciales incorrectas", "Usuario o contraseña incorrectos.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al intentar iniciar sesión.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
