package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.ReservacionDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Reservacion extends Stage {

    private VBox vbox;
    private Label lblTitulo, lblHora;
    private TextField txtIdCliente, txtNoPersonas, txtHora, txtMinuto;
    private DatePicker dpFecha;
    private ComboBox cbHora , cbMinutos;
    private Button btnAgregar;
    private TableView<ReservacionDAO> tbvReservacion;
    private ReservacionDAO reservacionDAO;
    private Scene scene;

    public Reservacion(TableView<ReservacionDAO> tbvReserva, ReservacionDAO objRe) {
        this.tbvReservacion = tbvReserva;
        CrearUI();
        if (objRe == null) {
            reservacionDAO = new ReservacionDAO();
        }else {
            reservacionDAO = objRe;
            txtIdCliente.setText(Integer.toString(reservacionDAO.getId_cliente()));
            txtNoPersonas.setText(Integer.toString(reservacionDAO.getNo_personas()));
            dpFecha.setValue(LocalDate.now());
        }
        this.setTitle("Agregar o editar una reservacion");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        txtIdCliente = new TextField();
        lblTitulo = new Label("Agrega o editar una reservacion");
        txtIdCliente.setPromptText("Numero de Cliente");
        txtNoPersonas = new TextField();
        txtNoPersonas.setPromptText("Numero de Persona");

        dpFecha = new DatePicker();
        dpFecha.setValue(LocalDate.now());
        HBox hbFecha = new HBox(5, new Label("Fecha"), dpFecha);
        hbFecha.setAlignment(Pos.CENTER);

        cbHora = new ComboBox<>();
        cbHora.getItems().addAll(IntStream.rangeClosed(11,22).boxed().collect(Collectors.toList()));
        cbHora.setValue(12);

        cbMinutos = new ComboBox<>();
        cbMinutos.getItems().addAll(0, 15, 30, 45);
        cbMinutos.setValue(0);
        lblHora = new Label();

        HBox hBox = new HBox(lblHora, cbHora,new Label(" : ") ,cbMinutos);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        LocalDateTime fechaHora = LocalDateTime.of(
                dpFecha.getValue(),
                LocalTime.of((Integer) cbHora.getValue(), (Integer) cbMinutos.getValue())
        );
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> {
            reservacionDAO.setId_cliente(Integer.parseInt(txtIdCliente.getText()));
            reservacionDAO.setNo_personas(Integer.parseInt(txtNoPersonas.getText()));
            reservacionDAO.setFecha_reservacion(Timestamp.valueOf(fechaHora));
            if (reservacionDAO.getId_reservacion() > 0){
                reservacionDAO.updateReservacion();
            }else reservacionDAO.insertReservacionAdmin();
            tbvReservacion.setItems(reservacionDAO.selectAllReservacion());
            tbvReservacion.refresh();
            this.close();
        });
        vbox = new VBox(lblTitulo,txtIdCliente, txtNoPersonas, hbFecha, hBox, btnAgregar);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 400, 400);
    }
}
