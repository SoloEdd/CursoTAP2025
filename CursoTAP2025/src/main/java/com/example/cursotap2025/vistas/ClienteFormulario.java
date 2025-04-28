package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.ClienteDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ClienteFormulario extends Stage {
    private Consumer<ClienteDAO> callback;

    public ClienteFormulario(Consumer<ClienteDAO> callback) {
        this.callback = callback;
        crearUI();
        this.setTitle("Nuevo Cliente");
    }

    private void crearUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField tfNombre = new TextField();
        TextField tfTelefono = new TextField();
        TextField tfEmail = new TextField();
        TextField tfDireccion = new TextField();

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(tfNombre, 1, 0);
        grid.add(new Label("Teléfono:"), 0, 1);
        grid.add(tfTelefono, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(tfEmail, 1, 2);
        grid.add(new Label("Dirección:"), 0, 3);
        grid.add(tfDireccion, 1, 3);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            if (validarCampos(tfNombre, tfTelefono)) {
                ClienteDAO nuevoCliente = new ClienteDAO();
                nuevoCliente.setNomCte(tfNombre.getText());
                nuevoCliente.setTelCte(tfTelefono.getText());
                nuevoCliente.setEmailCte(tfEmail.getText());
                nuevoCliente.setDireccion(tfDireccion.getText());

                if (nuevoCliente.insertCliente()) {
                    callback.accept(nuevoCliente);
                    this.close();
                } else {
                    mostrarError("No se pudo guardar el cliente");
                }
            }
        });

        grid.add(btnGuardar, 1, 4);
        Scene scene = new Scene(grid);
        this.setScene(scene);
    }

    private boolean validarCampos(TextField nombre, TextField telefono) {
        if (nombre.getText().isEmpty() || telefono.getText().isEmpty()) {
            mostrarError("Nombre y teléfono son campos obligatorios");
            return false;
        }
        return true;
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
