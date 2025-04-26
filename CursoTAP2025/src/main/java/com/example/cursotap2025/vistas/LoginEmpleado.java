package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.EmpleadoDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginEmpleado extends Stage {

    private Label lblTitulo, lblUsername, lblPassword;
    private TextField tfUsuario;
    private PasswordField pfPassword;
    private Button btnLogin, btnAdmin;
    private EmpleadoDAO empleado;
    private VBox vBox;
    private Scene scene;

    public LoginEmpleado() {
        crearUI();
        this.setTitle("Login Empleado - Restaurante");
        //this.setResizable(false);
        this.setScene(scene);
        this.show();
    }

    private void crearUI() {
        lblTitulo = new Label("Sistema de Órdenes del Restaurante");
        lblTitulo.getStyleClass().add("titulo");

        lblUsername = new Label("Ingrese usuario");
        lblUsername.getStyleClass().add("subtitulo");

        tfUsuario = new TextField();
        tfUsuario.setPromptText("Usuario");

        lblPassword = new Label("ingrese password");
        lblPassword.getStyleClass().add("subtitulo");

        pfPassword = new PasswordField();
        pfPassword.setPromptText("Contraseña");

        btnLogin = new Button("Iniciar Sesión");
        btnLogin.getStyleClass().add("btn-login");
        //btnLogin.setDefaultButton(true);
        btnAdmin = new Button("Iniciar sesion como administrador");
        btnAdmin.getStyleClass().add("btn-admin");

        btnLogin.setOnAction(e -> {
            validarCredenciales();
        });

        btnAdmin.setOnAction(e -> {
            new LoginAdmin();
            this.close();
        });

        vBox= new VBox(15, lblTitulo, lblUsername, tfUsuario, lblPassword, pfPassword, btnLogin, btnAdmin);
        vBox.setPadding(new Insets(30));
        vBox.setAlignment(Pos.CENTER);
        scene = new Scene(vBox, 400, 600);
        scene.getStylesheets().add(getClass().getResource("/Styles/loginempleado.css").toExternalForm());
    }

    private void validarCredenciales() {
        String usuario = tfUsuario.getText();
        String password = pfPassword.getText();
        if (usuario.isEmpty() || password.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacios", "Por favor, complete todos los campos.");
            return;
        }
        empleado = EmpleadoDAO.autenticar(usuario, password);
        if (empleado != null) {
            new SistemaOrdenes(empleado);
            this.close();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Credenciales incorrectas", "Usuario o contraseña incorrectos o no encontrados.");
        }
    }

    private void mostrarAlerta(Alert.AlertType alertType, String titulo, String mensaje) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
