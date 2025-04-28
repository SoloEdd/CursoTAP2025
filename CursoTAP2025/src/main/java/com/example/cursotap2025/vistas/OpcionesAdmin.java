package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.GeneradorReporteVentasDia;
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
    private Button btnEmpleados, btnClientes, btnProductos, btnOrdenes, btnRecervaciones, btnCerrarSesion;
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
        btnRecervaciones = new Button("Recervaciones");
        btnCerrarSesion = new Button("Cerrar Sesion");
        btnCerrarSesion.setId("cerrarSesion");
        lblMenu.setId("menuTitulo");

        sidebar = new VBox(20, lblMenu, btnEmpleados, btnClientes, btnProductos, btnOrdenes, btnRecervaciones, btnCerrarSesion);
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
        btnClientes.setOnAction(e -> {
            mostrarVista(new ListaClientes());
            root.setRight(null);
        });

        btnProductos.setOnAction(e -> {
            mostrarVista(new ListaProductos());
            root.setRight(new ProductoTop());
        });

        btnOrdenes.setOnAction(e -> {
            mostrarVista(new ListaOrden());
            Button btnReporteVentas = new Button("Generar Reporte del Día");
            btnReporteVentas.setOnAction(event -> GeneradorReporteVentasDia.generarReporte());

            VBox rightPanel = new VBox(20, btnReporteVentas);
            rightPanel.setPadding(new Insets(15));
            root.setRight(rightPanel);
        });

        btnRecervaciones.setOnAction(e -> {
            mostrarVista(new ListaReservacion());
            root.setRight(null);
        });

        btnCerrarSesion.setOnAction(e -> {
            this.close();
            new LoginEmpleado();
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
