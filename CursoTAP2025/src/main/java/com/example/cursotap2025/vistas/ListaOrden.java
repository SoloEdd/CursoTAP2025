package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.OrdenDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Timestamp;

public class ListaOrden extends Stage {

    private TableView<OrdenDAO> tbvOrdenes;
    private VBox vBox;
    private Scene scene;
    private Button eliminarOrden;

    public ListaOrden() {
        CrearUI();
        this.setTitle("Lista de Ordenes");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        tbvOrdenes = new TableView<>();
        eliminarOrden = new Button("Eliminar");
        CreateTable();
        vBox = new VBox(tbvOrdenes);
        scene = new Scene(vBox, 800, 600);
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
