package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.EmpleadoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SistemaOrdenes extends Stage {

    private EmpleadoDAO empleado;

    public SistemaOrdenes(EmpleadoDAO empleado) {
        this.empleado = empleado;
        crearUI();
        this.setTitle("Sistema de Órdenes - " + empleado.getNombre());
        this.show();
    }

    private void crearUI() {
        BorderPane root = new BorderPane();

        Label lblEmpleado = new Label("Empleado: " + empleado.getNombre() + " " +
                empleado.getPrimer_apellido() + " (" + obtenerPuesto() + ")");
        lblEmpleado.setStyle("-fx-font-weight: bold; -fx-padding: 10px;");
        root.setTop(lblEmpleado);

        // Aquí irían los demás componentes de la interfaz (mesas, menú, etc.)
        // ...

        Scene scene = new Scene(root, 800, 600);
        this.setScene(scene);
    }

    private String obtenerPuesto() {
        // Método para obtener el nombre del puesto basado en id_puesto
        // Implementar según tu base de datos
        return switch(empleado.getId_puesto()) {
            case 1 -> "Mesero";
            case 2 -> "Cajero";
            case 3 -> "Administrador";
            default -> "Empleado";
        };
    }
}
