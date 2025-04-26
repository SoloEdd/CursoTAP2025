package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.CategoriaDAO;
import com.example.cursotap2025.models.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaProductos extends VBox {

    private ToolBar toolBar;
    private TableView<CategoriaDAO> tbvCategoria;
    private TableView<ProductoDAO> tbvProducto;

    private Button btnAgregarCategoria, btnAgregarProducto;

    public ListaProductos(){
        CrearUI();
    }

    private void CrearUI(){
        tbvCategoria = new TableView<>();
        tbvProducto = new TableView<>();
        btnAgregarCategoria = new Button("Agregar Categoria");
        btnAgregarProducto = new Button("Agregar Producto");
        btnAgregarCategoria.setOnAction(e -> new Categoria(tbvCategoria, null));
        btnAgregarProducto.setOnAction(e -> new Producto(tbvProducto, null));
        toolBar = new ToolBar(btnAgregarCategoria, btnAgregarProducto);
        CreateTableCategoria();
        CreateTableProductos();

        this.getChildren().addAll(toolBar, tbvCategoria, tbvProducto);
        this.setSpacing(10);
        this.getStylesheets().add(getClass().getResource("/Styles/ListaProductos.css").toExternalForm());

    }

    private void CreateTableCategoria(){
        CategoriaDAO objCategoria = new CategoriaDAO();
        TableColumn<CategoriaDAO, Integer> colIdCategoria = new TableColumn<>("Numero de categoria");
        colIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        TableColumn<CategoriaDAO, String> colNombreCategoria = new TableColumn<>("Tipo de categoria");
        colNombreCategoria.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));
        TableColumn<CategoriaDAO, String> colDescripcionCategoria = new TableColumn<>("Descripcion");
        colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<>("descripcionCategoria"));
        tbvCategoria.getColumns().addAll(colIdCategoria, colNombreCategoria, colDescripcionCategoria);
        tbvCategoria.setItems(objCategoria.selectCategoria());
    }

    private void CreateTableProductos(){
        ProductoDAO objProducto = new ProductoDAO();
        TableColumn<ProductoDAO, Integer> colIdProducto = new TableColumn<>("ID de producto");
        colIdProducto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        TableColumn<ProductoDAO, String> colNombreProducto = new TableColumn<>("Nombre de producto");
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));
        TableColumn<ProductoDAO, Integer> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<ProductoDAO, Integer> colCosto = new TableColumn<>("Costo");
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        TableColumn<ProductoDAO, Integer> colIdCategoria = new TableColumn<>("Categoria");
        colIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        tbvProducto.getColumns().addAll(colIdProducto, colNombreProducto, colPrecio, colCosto, colIdCategoria);
        tbvProducto.setItems(objProducto.selectProducto());
    }

}
