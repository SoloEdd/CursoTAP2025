package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.ReservacionDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Timestamp;

public class Reservacion extends Stage {

    private VBox vbox;
    private Label lblTitulo;
    private TextField txtIdCliente, txtNoPersonas, txtFecha;
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
            txtNoPersonas.setText(Integer.toString(reservacionDAO.getNo_persona()));
            txtFecha.setText(String.valueOf(reservacionDAO.getFecha_reservacion()));
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
        txtFecha = new TextField();
        txtFecha.setPromptText("Fecha: DD/MM/YYYY");
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> {
            reservacionDAO.setId_cliente(Integer.parseInt(txtIdCliente.getText()));
            reservacionDAO.setNo_persona(Integer.parseInt(txtNoPersonas.getText()));
            reservacionDAO.setFecha_reservacion(Timestamp.valueOf(txtFecha.getText()));
            if (reservacionDAO.getId_reservacion() > 0){
                reservacionDAO.updateReservacion();
            }else reservacionDAO.insertReservacion();
            tbvReservacion.setItems(reservacionDAO.selectAllReservacion());
            tbvReservacion.refresh();
            this.close();
        });
        vbox = new VBox(lblTitulo,txtIdCliente, txtNoPersonas, txtFecha, btnAgregar);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 400, 400);
    }
}
