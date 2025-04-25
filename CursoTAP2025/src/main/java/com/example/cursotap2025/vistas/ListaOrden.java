package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.OrdenDAO;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.sql.Timestamp;

public class ListaOrden extends VBox {

    private TableView<OrdenDAO> tbvOrdenes;

    public ListaOrden() {
        CrearUI();
    }

    private void CrearUI() {
        tbvOrdenes = new TableView<>();
        CreateTable();
        this.getChildren().add(tbvOrdenes);
        this.setSpacing(10);
        this.getStylesheets().add(getClass().getResource("/Styles/Ordenes.css").toExternalForm());
    }

    private void CreateTable() {
        OrdenDAO ordenDAO = new OrdenDAO();
        TableColumn<OrdenDAO, Integer> colIdOrden = new TableColumn<>("Numero de Orden");
        colIdOrden.setCellValueFactory(new PropertyValueFactory<>("id_orden"));
        TableColumn<OrdenDAO, Integer> colCte = new TableColumn<>("Numero de cliente");
        colCte.setCellValueFactory(new PropertyValueFactory<>("idCte"));
        TableColumn<OrdenDAO, String> colEmailCte = new TableColumn<>("Email del Cliente");
        colEmailCte.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));
        TableColumn<OrdenDAO, Integer> colNoMesa = new TableColumn<>("Numero de mesa");
        colNoMesa.setCellValueFactory(new PropertyValueFactory<>("no_mesa"));
        TableColumn<OrdenDAO, Integer> colIdEmpleado = new TableColumn<>("Numero de Empleado");
        colIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("id_empleado"));
        TableColumn<OrdenDAO, String> colNombreEmpleado = new TableColumn<>("Nombre del Empleado");
        colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
        TableColumn<OrdenDAO, Timestamp> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbvOrdenes.getColumns().addAll(colIdOrden, colCte, colEmailCte, colNoMesa, colIdEmpleado, colNombreEmpleado, colFecha);
        tbvOrdenes.setItems(ordenDAO.selectOrden());
    }
}
