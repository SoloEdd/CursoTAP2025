package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.CategoriaDAO;
import com.example.cursotap2025.models.ProductoDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Producto extends Stage {

    private TextField txtNombreProducto, txtPrecioProducto, txtCostoProducto, txtCategoriaProducto;
    private Button btnAgregarProducto;
    private VBox vBox;
    private Scene scene;
    private ProductoDAO objProducto;
    private TableView<ProductoDAO> tbvProducto;

    public Producto(TableView<ProductoDAO> tbvProducto, ProductoDAO objP) {
        this.tbvProducto = tbvProducto;
        CrearUI();
        if (objP == null) {
            objProducto = new ProductoDAO();
        }else {
            objProducto = objP;
            txtNombreProducto.setText(objProducto.getNombre_producto());
            txtPrecioProducto.setText(Double.toString(objProducto.getPrecio()));
            txtCostoProducto.setText(Double.toString(objProducto.getCosto()));
            txtCategoriaProducto.setText(Integer.toString(objProducto.getId_categoria()));
        }
        this.setTitle("Agregar o editar un producto");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        txtNombreProducto = new TextField();
        txtNombreProducto.setPromptText("Nombre");
        txtPrecioProducto = new TextField();
        txtPrecioProducto.setPromptText("Precio");
        txtCostoProducto = new TextField();
        txtCostoProducto.setPromptText("Costo");
        txtCategoriaProducto = new TextField();
        txtCategoriaProducto.setPromptText("Categoria");
        btnAgregarProducto = new Button("Agregar");
        btnAgregarProducto.setOnAction(e -> {
            if (validarCampos()) {
                objProducto.setNombre_producto(txtNombreProducto.getText());
                objProducto.setPrecio(Double.parseDouble(txtPrecioProducto.getText()));
                objProducto.setCosto(Double.parseDouble(txtCostoProducto.getText()));
                objProducto.setId_categoria(Integer.parseInt(txtCategoriaProducto.getText()));
                if (objProducto.getId_producto() > 0) {
                    objProducto.updateProducto();
                } else objProducto.insertProducto();
                tbvProducto.setItems(objProducto.selectProducto());
                tbvProducto.refresh();
                this.close();
            }
        });
        vBox = new VBox(txtNombreProducto, txtPrecioProducto, txtCostoProducto, txtCategoriaProducto, btnAgregarProducto);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        scene = new Scene(vBox, 400,400);
        scene.getStylesheets().add(getClass().getResource("/Styles/Categoria.css").toExternalForm());
    }

    private boolean validarCampos() {
        if (txtNombreProducto.getText().trim().isEmpty() ||
                txtPrecioProducto.getText().trim().isEmpty() ||
                txtCostoProducto.getText().trim().isEmpty() ||
                txtCategoriaProducto.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campos vacíos", "Por favor llena todos los campos.");
            return false;
        }

        try {
            Double.parseDouble(txtPrecioProducto.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Precio inválido", "El precio debe ser un número válido.");
            return false;
        }

        try {
            Double.parseDouble(txtCostoProducto.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Costo inválido", "El costo debe ser un número válido.");
            return false;
        }

        int idCategoria;
        try {
            idCategoria = Integer.parseInt(txtCategoriaProducto.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Categoría inválida", "La categoría debe ser un número entero.");
            return false;
        }

        if (!categoriaExiste(idCategoria)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Categoría no encontrada", "La categoría ingresada no existe en la base de datos.");
            return false;
        }

        return true;
    }

    private boolean categoriaExiste(int idCategoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.buscarCategoriaPorId(idCategoria) != null;
    }


    private void mostrarAlerta(Alert.AlertType alertType, String titulo, String mensaje) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
