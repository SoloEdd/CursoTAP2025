package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.OrdenDAO;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Orden extends Stage {

    private TextField txtIdOrden, txtidCte, txtemailCliente, txtNoMesa, txtidEmpleado, txtNombreEmpleado, txtFecha;
    private VBox vBox;
    private Scene scene;
    private OrdenDAO objOrden;
    private TableView<OrdenDAO> tbvOrden;

    public Orden(TableView<OrdenDAO> tbvOrden, OrdenDAO objOrden) {
        this.tbvOrden = tbvOrden;
        CrearUI();
        this.setTitle("Orden registradas");
        this.show();
    }

    public void CrearUI() {
        txtIdOrden = new TextField();
        txtIdOrden.setPromptText("Numero de Orden");
        txtidCte = new TextField();
        txtidCte.setPromptText("Numero de cliente");
        txtemailCliente = new TextField();
        txtemailCliente.setPromptText("Email del cliente");
        txtNoMesa = new TextField();
        txtNoMesa.setPromptText("Numero de mesa");
        txtNombreEmpleado = new TextField();
        txtNombreEmpleado.setPromptText("Nombre del empleado");
        txtFecha = new TextField();
        txtFecha.setPromptText("Fecha");
        vBox = new VBox(txtidCte, txtemailCliente, txtIdOrden, txtNombreEmpleado, txtFecha);
    }

}
