package com.example.cursotap2025.componentes;

import com.example.cursotap2025.models.EmpleadoDAO;
import com.example.cursotap2025.models.PuestoTrabajoDAO;
import com.example.cursotap2025.vistas.Empleado;
import com.example.cursotap2025.vistas.PuestoTrabajo;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCellPuesto extends TableCell<PuestoTrabajoDAO, String> {

    private Button btnCell;
    private String strLabelBtn;

    public ButtonCellPuesto(String label) {
        strLabelBtn = label;
        btnCell = new Button(strLabelBtn);
        btnCell.setOnAction(e -> {
            PuestoTrabajoDAO objPT = this.getTableView().getItems().get(getIndex());
            if(strLabelBtn.equals("Editar")) {
                new PuestoTrabajo(this.getTableView(), objPT);
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMACION");
                alert.setContentText("¿Desea eliminar el registro?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    objPT.deletePuestoTrabajo();
                }
            }
            this.getTableView().setItems(objPT.selectAllPuestoTrabajo());
            this.getTableView().refresh();
        });
    }

    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty) {
            this.setGraphic(btnCell);
        }
    }
}
