package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.ClienteDAO;
import com.example.cursotap2025.models.ReservacionDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FormularioReservacion extends Stage {
    private DatePicker dpFecha;
    private ComboBox<Integer> cbHora;
    private ComboBox<Integer> cbMinutos;
    private TextField tfNumPersonas;
    private ComboBox<ClienteDAO> cbClientes;

    public FormularioReservacion() {
        crearUI();
        this.setTitle("Nueva Reservación");
        this.show();
    }

    private void crearUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Controles del formulario
        dpFecha = new DatePicker();
        dpFecha.setValue(LocalDate.now());

        cbHora = new ComboBox<>();
        cbHora.getItems().addAll(IntStream.rangeClosed(11, 22).boxed().collect(Collectors.toList()));
        cbHora.setValue(12);

        cbMinutos = new ComboBox<>();
        cbMinutos.getItems().addAll(0, 15, 30, 45);
        cbMinutos.setValue(0);

        tfNumPersonas = new TextField();
        tfNumPersonas.setText("2");

        cbClientes = new ComboBox<>();
        cbClientes.setItems(new ClienteDAO().selectCliente());
        cbClientes.setPromptText("Seleccione un cliente");

        // Configurar cómo mostrar clientes
        cbClientes.setCellFactory(param -> new ListCell<ClienteDAO>() {
            @Override
            protected void updateItem(ClienteDAO cliente, boolean empty) {
                super.updateItem(cliente, empty);
                setText(empty || cliente == null ? null : cliente.getNomCte() + " - " + cliente.getTelCte());
            }
        });

        // Botón para guardar
        Button btnGuardar = new Button("Guardar Reservación");
        btnGuardar.setOnAction(e -> guardarReservacion());

        // Organizar interfaz
        grid.add(new Label("Fecha:"), 0, 0);
        grid.add(dpFecha, 1, 0);
        grid.add(new Label("Hora:"), 0, 1);
        grid.add(new HBox(5, cbHora, new Label(":"), cbMinutos), 1, 1);
        grid.add(new Label("N° Personas:"), 0, 2);
        grid.add(tfNumPersonas, 1, 2);
        grid.add(new Label("Cliente:"), 0, 3);
        grid.add(cbClientes, 1, 3);
        grid.add(btnGuardar, 1, 4);

        Scene scene = new Scene(grid);
        this.setScene(scene);
    }

    private void guardarReservacion() {
        if (cbClientes.getSelectionModel().isEmpty()) {
            mostrarAlerta("Error", "Seleccione un cliente", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Crear timestamp combinando fecha y hora
            LocalDateTime fechaHora = LocalDateTime.of(
                    dpFecha.getValue(),
                    LocalTime.of(cbHora.getValue(), cbMinutos.getValue())
            );

            ReservacionDAO reservacion = new ReservacionDAO();
            reservacion.setId_cliente(cbClientes.getValue().getIdCliente());
            reservacion.setNo_personas(Integer.parseInt(tfNumPersonas.getText()));
            reservacion.setFecha_reservacion(Timestamp.valueOf(fechaHora));

            if (reservacion.insertReservacion()) {
                mostrarAlerta("Éxito", "Reservación registrada correctamente", Alert.AlertType.INFORMATION);
                this.close();
            } else {
                mostrarAlerta("Error", "No se pudo guardar la reservación", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Número de personas inválido", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
