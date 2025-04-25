package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginCliente extends Stage {

    private Label lblUsuario, lblContrasena;
    private TextField txtUsuario;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Button btnInvitado;
    private Scene scene;
    private VBox vbox;

    public LoginCliente() {
        CrearUI();
        this.setTitle("Login Cliente");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {

        lblUsuario = new Label("Usuario");
        txtUsuario = new TextField();
        txtUsuario.setPromptText("Ingrese su email");
        lblContrasena = new Label("Contrasena");
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Ingrese su contrasena");
        btnLogin = new Button("Inciar sesion");
        btnInvitado = new Button("Iniciar sesion como invitado");

        btnLogin.setOnAction(e -> vaidarCredenciales());
        vbox = new VBox(5);
        vbox.getChildren().addAll(lblUsuario, txtUsuario, lblContrasena, txtPassword, btnLogin, btnInvitado);
        scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("/Styles/logincliente.css").toExternalForm());

    }

    private void vaidarCredenciales() {
        ClienteDAO cliente = new ClienteDAO();
        String email = txtUsuario.getText();
        String pass = txtPassword.getText();
        if (cliente.login(email, pass)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Inicio de sesión exitoso.");
            alert.showAndWait();
            // Aquí puedes abrir la ventana principal del cliente
            this.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Correo o contraseña incorrectos.");
            alert.showAndWait();
        }
    }

}
