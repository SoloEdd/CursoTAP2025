package com.example.cursotap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OpcionesAdmin extends Stage {

    private HBox hbox;
    private VBox vbox;
    private Button btnEmpleados, btnClientes, btnProductos, btnOrdenes;
    private Label lblMenu;
    private Scene scene;

    public OpcionesAdmin() {
        CrearUI();
        this.setTitle("Opciones de Administrador");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        lblMenu = new Label("Elija alguna opcion para mostrar");
        btnClientes = new Button("Ver Clientes");
        btnEmpleados = new Button("Ver Empleados");
        btnProductos = new Button("Ver Productos");
        btnOrdenes = new Button("Ver Ordenes");
        hbox = new HBox(btnClientes, btnEmpleados, btnProductos, btnOrdenes);
        vbox = new VBox(lblMenu, hbox);
        scene = new Scene(vbox, 400,400);
    }

}
