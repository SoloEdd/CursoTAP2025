package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.EmpleadoDAO;
import com.example.cursotap2025.models.GeneradorReporteEmpleados;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EmpleadoTop extends VBox {

    private Label lblTitulo;
    private Label lblNombre;
    private Label lblNumVentas;
    private Label lblTotal;
    private EmpleadoDAO topEmpleado;
    private Button btnReporte;



    public EmpleadoTop() {
        topEmpleado = new EmpleadoDAO();
        topEmpleado.obtenerEmpleadoTopVentas();

        if (topEmpleado != null) {
            lblTitulo = new Label("Empleado con más ventas");
            lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            lblNombre = new Label("Nombre: " + topEmpleado.getNombre() + " " +
                    topEmpleado.getPrimer_apellido() + " " + topEmpleado.getSegundo_apellido());
            lblNumVentas = new Label("Número de ventas: " + topEmpleado.getNumVentas());
            lblTotal = new Label("Total Vendido: $" + String.format("%.2f", topEmpleado.getTotalVendido()));
            btnReporte = new Button("Generar Reporte");
            // En tu clase EmpleadoTop donde está el botón:
            btnReporte.setOnAction(e -> {
                ObservableList<EmpleadoDAO> empleados = new EmpleadoDAO().selectEmpleado();
                EmpleadoDAO topEmpleado = EmpleadoDAO.obtenerEmpleadoTopVentas();
                GeneradorReporteEmpleados.generarReporte(empleados, topEmpleado);
            });
            this.getChildren().addAll(lblTitulo, lblNombre, lblNumVentas, lblTotal, btnReporte);
        }else this.getChildren().add(new Label("No se encontraron registros de ventas."));

        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: #f0f4f8; -fx-border-color: #cccccc; -fx-border-radius: 10;");
    }
}
