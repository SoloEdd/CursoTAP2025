package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.CategoriaDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Categoria extends Stage {

    private TextField txtNombreCategoria, txtDescripcionCategoria;
    private Button btnGuardarCategoria;
    private VBox vBox;
    private Scene scene;
    private CategoriaDAO objCategoria;
    private TableView<CategoriaDAO> tbvCategoria;

    public Categoria(TableView<CategoriaDAO> tbvCategoria, CategoriaDAO objCa) {
        this.tbvCategoria = tbvCategoria;
        CrearUI();
        if (objCa == null){
            objCategoria = new CategoriaDAO();
        }else {
            objCategoria = objCa;
            txtNombreCategoria.setText(objCategoria.getNombreCategoria());
            txtDescripcionCategoria.setText(objCategoria.getDescripcionCategoria());
        }
        this.setTitle("Agregar una nueva categoria");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        txtNombreCategoria = new TextField();
        txtNombreCategoria.setPromptText("Nombre de la categoria");
        txtDescripcionCategoria = new TextField();
        txtDescripcionCategoria.setPromptText("Descripcion de la categoria");
        btnGuardarCategoria = new Button("Guardar");
        btnGuardarCategoria.setOnAction(e -> {
            ValidarCampos();
        });
        vBox = new VBox( txtNombreCategoria, txtDescripcionCategoria, btnGuardarCategoria);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.CENTER);
        scene = new Scene(vBox, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/Styles/categoria.css").toExternalForm());
    }

    private void ValidarCampos() {
        if (txtNombreCategoria.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "El campo 'Nombre de la categoría' no puede estar vacío.");
            txtNombreCategoria.requestFocus();
            return;
        }
        if (txtDescripcionCategoria.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "El campo 'Descripción de la categoría' no puede estar vacío.");
            txtDescripcionCategoria.requestFocus();
            return;
        }
        guardarCategoria();
    }

    private void guardarCategoria() {
        objCategoria.setNombreCategoria(txtNombreCategoria.getText());
        objCategoria.setDescripcionCategoria(txtDescripcionCategoria.getText());
        if (objCategoria.getId_categoria() > 0){
            objCategoria.updateCategoria();
        } else {
            objCategoria.insertCategoria();
        }
        tbvCategoria.setItems(objCategoria.selectCategoria());
        tbvCategoria.refresh();
        this.close();
    }


    private void mostrarAlerta(Alert.AlertType alertType, String titulo, String mensaje) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
