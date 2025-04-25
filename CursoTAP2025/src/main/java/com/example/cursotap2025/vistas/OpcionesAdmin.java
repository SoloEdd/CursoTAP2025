package com.example.cursotap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpcionesAdmin extends Stage {

    private BorderPane root;
    private VBox sidebar;
    private Pane contentPane;
    private Button btnEmpleados, btnClientes, btnProductos, btnOrdenes, btnCerrarSesion;
    private Label lblMenu;
    private Scene scene;

    public OpcionesAdmin() {
        CrearUI();
        this.setTitle("Opciones de Administrador");
        this.setScene(scene);
        this.setMaximized(true);
        this.show();
    }

    private void CrearUI() {
        lblMenu = new Label("Menu principal");
        btnEmpleados = new Button("Empleados");
        btnClientes = new Button("Clientes");
        btnProductos = new Button("Productos");
        btnOrdenes = new Button("Ordenes");
        btnCerrarSesion = new Button("Cerrar Sesion");
        btnCerrarSesion.setId("cerrarSesion");
        lblMenu.setId("menuTitulo");

        sidebar = new VBox(20, lblMenu, btnEmpleados, btnClientes, btnProductos, btnOrdenes, btnCerrarSesion);
        sidebar.setPadding(new Insets(30));
        sidebar.setAlignment(Pos.CENTER_LEFT);
        sidebar.setStyle("-fx-background-color: #f8f9fa;");
        sidebar.setPrefWidth(250);

        contentPane = new StackPane();
        contentPane.setStyle("-fx-background-color: white;");

        btnEmpleados.setOnAction(e -> {
            mostrarVista(new ListaEmpleado());
            root.setRight(new EmpleadoTop());
        });
        btnClientes.setOnAction(e -> mostrarVista(new ListaClientes()));
        btnProductos.setOnAction(e -> mostrarVista(new ListaProductos()));
        btnOrdenes.setOnAction(e -> mostrarVista(new ListaOrden()));
        btnCerrarSesion.setOnAction(e -> {
            this.close();
            new LoginAdmin().show();
        });

        root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(contentPane);

        this.initStyle(StageStyle.UNDECORATED);

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Styles/sidebar.css").toExternalForm());
    }

    private void mostrarVista(Node vista) {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(vista);
    }
}
