package com.example.cursotap2025.vistas;

import com.example.cursotap2025.componentes.ButtonCellReservacion;
import com.example.cursotap2025.models.ReservacionDAO;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.sql.Timestamp;

public class ListaReservacion extends VBox {

    private TableView<ReservacionDAO> tbvReservacion;
    private ToolBar toolbar;
    private Button btnAgregarReservacion;

    public ListaReservacion() {
        CrearUI();
    }

    private void CrearUI() {
        tbvReservacion = new TableView<>();
        btnAgregarReservacion = new Button("Agregar Reservacion");
        btnAgregarReservacion.setOnAction(e -> new Reservacion(tbvReservacion, null));
        toolbar = new ToolBar(btnAgregarReservacion);
        CreateTable();
        this.getChildren().addAll(toolbar, tbvReservacion);
        this.setSpacing(10);
    }

    private void CreateTable() {
        ReservacionDAO dao = new ReservacionDAO();
        TableColumn<ReservacionDAO, Integer> colIdReservacion = new TableColumn<>("Numero de Reservacion");
        colIdReservacion.setCellValueFactory(new PropertyValueFactory<>("id_reservacion"));
        TableColumn<ReservacionDAO, Integer> colIdCte = new TableColumn<>("Numero de cliente");
        colIdCte.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        TableColumn<ReservacionDAO, Timestamp> colFecha = new TableColumn<>("Fecha de Reserva");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha_reservacion"));
        TableColumn<ReservacionDAO, Integer> colNoPersonas = new TableColumn<>("Numero de personas");
        colNoPersonas.setCellValueFactory(new PropertyValueFactory<>("no_personas"));
        TableColumn<ReservacionDAO, String> colEditar = new TableColumn<>("Editar");
        colEditar.setCellFactory(new Callback<TableColumn<ReservacionDAO, String>, TableCell<ReservacionDAO, String>>() {
            @Override
            public TableCell<ReservacionDAO, String> call(TableColumn<ReservacionDAO, String> reservacionDAOStringTableColumn) {
                return new ButtonCellReservacion("Editar");
            }
        });
        TableColumn<ReservacionDAO, String> colEliminar = new TableColumn<>("Eliminar");
        colEliminar.setCellFactory(new Callback<TableColumn<ReservacionDAO, String>, TableCell<ReservacionDAO, String>>() {
            @Override
            public TableCell<ReservacionDAO, String> call(TableColumn<ReservacionDAO, String> reservacionDAOStringTableColumn) {
                return new ButtonCellReservacion("Eliminar");
            }
        });

        tbvReservacion.getColumns().addAll(colIdReservacion, colIdCte, colFecha, colNoPersonas, colEditar, colEliminar);
        tbvReservacion.setItems(dao.selectAllReservacion());
    }

}
