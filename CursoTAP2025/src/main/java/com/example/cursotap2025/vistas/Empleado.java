package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.EmpleadoDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Empleado extends Stage {

    private Button btnGuardar;
    private TextField txtPrimerApellido, txtSegundoApellido, txtNombre, txtCURP, txtRFC, txtSueldo , txtCelEmpl, txtNSS, txtHorario, txtFechaIngreso, txtIdPuesto;
    private VBox vBox;
    private Scene scene;
    private EmpleadoDAO objEmpleado;
    private TableView<EmpleadoDAO> tbvEmpleados;

    public Empleado(TableView<EmpleadoDAO> tbvEmpleados, EmpleadoDAO objE) {
        this.tbvEmpleados = tbvEmpleados;
        CrearUI();
        if(objE == null){
            objEmpleado = new EmpleadoDAO();
        }else {
            objEmpleado = objE;
            txtPrimerApellido.setText(objEmpleado.getNombre());
            txtSegundoApellido.setText(objEmpleado.getSegundo_apellido());
            txtNombre.setText(objEmpleado.getNombre());
            txtCURP.setText(objEmpleado.getCurp());
            txtRFC.setText(objEmpleado.getRfc());
            txtSueldo.setText(String.format("%.2f", objEmpleado.getSueldo()));
            txtCelEmpl.setText(objEmpleado.getCelEmp());
            txtNSS.setText(objEmpleado.getNssEmp());
            txtHorario.setText(objEmpleado.getHorario());
            txtFechaIngreso.setText(objEmpleado.getFecha_ingreso());
            txtIdPuesto.setText(String.valueOf(objEmpleado.getId_empleado()));
        }
        this.setTitle("Agregar Empleado");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        txtPrimerApellido = new TextField();
        txtPrimerApellido.setPromptText("Primer Apellido");
        txtSegundoApellido = new TextField();
        txtSegundoApellido.setPromptText("Segundo Apellido");
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del Empleado");
        txtCURP = new TextField();
        txtCURP.setPromptText("CURP del Empleado");
        txtRFC = new TextField();
        txtRFC.setPromptText("RFC del Empleado");
        txtSueldo = new TextField();
        txtSueldo.setPromptText("Sueldo del Empleado");
        txtCelEmpl = new TextField();
        txtCelEmpl.setPromptText("Celular del Empleado");
        txtNSS = new TextField();
        txtNSS.setPromptText("NSS del Empleado");
        txtHorario = new TextField();
        txtHorario.setPromptText("Horas trabajadas del Empleado");
        txtFechaIngreso = new TextField();
        txtFechaIngreso.setPromptText("Fecha de Ingreso");
        txtIdPuesto = new TextField();
        txtIdPuesto.setPromptText("Puesto de trabajo");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            objEmpleado.setPrimer_apellido(txtPrimerApellido.getText());
            objEmpleado.setSegundo_apellido(txtSegundoApellido.getText());
            objEmpleado.setNombre(txtNombre.getText());
            objEmpleado.setCurp(txtCURP.getText());
            objEmpleado.setRfc(txtRFC.getText());
            objEmpleado.setSueldo(Double.parseDouble(txtSueldo.getText()));
            objEmpleado.setCelEmp(txtCelEmpl.getText());
            objEmpleado.setNssEmp(txtNSS.getText());
            objEmpleado.setHorario(txtHorario.getText());
            objEmpleado.setFecha_ingreso(txtFechaIngreso.getText());
            objEmpleado.setId_puesto(Integer.parseInt(txtIdPuesto.getText()));
            if (objEmpleado.getId_empleado() > 0){
                objEmpleado.updateEmpleado();
            }else {
                objEmpleado.insertarEmpleado();
            }
            tbvEmpleados.setItems(objEmpleado.selectEmpleado());
            tbvEmpleados.refresh();
            this.close();
        });
        vBox = new VBox(10, txtPrimerApellido, txtSegundoApellido, txtNombre, txtCURP, txtRFC, txtSueldo , txtCelEmpl, txtNSS, txtHorario, txtFechaIngreso, btnGuardar);
        vBox.setAlignment(Pos.CENTER);
        vBox.getStyleClass().add("vbox");
        scene = new Scene(vBox, 800, 800);
        scene.getStylesheets().add(getClass().getResource("/Styles/empleado_style.css").toExternalForm());
    }
}
