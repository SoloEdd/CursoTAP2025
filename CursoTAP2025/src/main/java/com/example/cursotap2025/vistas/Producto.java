package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.ProductoDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
        this.setTitle("Agregar un nuevo producto");
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
            objProducto.setNombre_producto(txtNombreProducto.getText());
            objProducto.setPrecio(Double.parseDouble(txtPrecioProducto.getText()));
            objProducto.setCosto(Double.parseDouble(txtCostoProducto.getText()));
            objProducto.setId_categoria(Integer.parseInt(txtCategoriaProducto.getText()));
            if (objProducto.getId_producto() > 0){
                objProducto.updateProducto();
            } else objProducto.insertProducto();
            tbvProducto.setItems(objProducto.selectProducto());
            tbvProducto.refresh();
            this.close();
        });
        vBox = new VBox(txtNombreProducto, txtPrecioProducto, txtCostoProducto, txtCategoriaProducto, btnAgregarProducto);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        scene = new Scene(vBox, 400,400);
        scene.getStylesheets().add(getClass().getResource("/Styles/Categoria.css").toExternalForm());
    }
}
