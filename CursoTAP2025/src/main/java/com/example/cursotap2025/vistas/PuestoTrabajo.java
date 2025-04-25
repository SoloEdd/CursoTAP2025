package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.PuestoTrabajoDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PuestoTrabajo extends Stage {

    private Button btnGuardar;
    private TextField  txtNombrePuesto, txtDescripcion;
    private VBox vbox;
    private Scene scene;
    private PuestoTrabajoDAO objPuesto;
    private TableView<PuestoTrabajoDAO> tbvPuestos;

    public PuestoTrabajo(TableView<PuestoTrabajoDAO> tbvPuestos, PuestoTrabajoDAO objPT) {
        this.tbvPuestos = tbvPuestos;
        CrearUI();
        if(objPT == null){
            objPuesto = new PuestoTrabajoDAO();
        }else {
            objPuesto = objPT;
            txtNombrePuesto.setText(objPuesto.getNombre_puesto());
            txtDescripcion.setText(objPuesto.getDescripcion());
        }
        this.setTitle("Agregar Puesto de trabajo");
        this.setScene(scene);
        this.show();
    }

    public void CrearUI() {
        txtNombrePuesto = new TextField();
        txtNombrePuesto.setPromptText("Puesto de trabajo");
        txtDescripcion = new TextField();
        txtDescripcion.setPromptText("Descripcion del trabajo");
        btnGuardar = new Button("Guardar Puesto de trabajo");
        btnGuardar.setOnAction(e -> {
            objPuesto.setNombre_puesto(txtNombrePuesto.getText());
            objPuesto.setDescripcion(txtDescripcion.getText());

            if(objPuesto.getId_puesto() > 0){
                objPuesto.updatePuestoTrabajo();
            }else {
                objPuesto.insertarPuestoTrabajo();
            }
            tbvPuestos.setItems(objPuesto.selectAllPuestoTrabajo());
            tbvPuestos.refresh();
            this.close();
        });
        vbox = new VBox(5, txtNombrePuesto, txtDescripcion, btnGuardar);
        vbox.setAlignment(Pos.CENTER);
        vbox.getStyleClass().add("vbox");
        scene = new Scene(vbox, 600,600);
        scene.getStylesheets().add(getClass().getResource("/Styles/empleado_style.css").toExternalForm());
    }
}
