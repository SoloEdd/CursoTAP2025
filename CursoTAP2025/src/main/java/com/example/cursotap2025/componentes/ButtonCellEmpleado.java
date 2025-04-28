package com.example.cursotap2025.componentes;

import com.example.cursotap2025.models.EmpleadoDAO;
import com.example.cursotap2025.vistas.Empleado;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCellEmpleado extends TableCell<EmpleadoDAO, String> {

    private Button btnCell;
    private String strLabelBtn;

    public ButtonCellEmpleado(String label) {
        strLabelBtn = label;
        btnCell = new Button(strLabelBtn);
        btnCell.setOnAction(e -> {
            EmpleadoDAO objEm = this.getTableView().getItems().get(getIndex());
            if(strLabelBtn.equals("Editar")) {
                new Empleado(this.getTableView(), objEm);
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMACION");
                alert.setContentText("¿Desea eliminar el registro?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    objEm.deleteEmpleado();
                }
            }
            this.getTableView().setItems(objEm.selectEmpleado());
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
