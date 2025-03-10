package com.example.cursotap2025.componentes;

import com.example.cursotap2025.models.ClienteDAO;
import com.example.cursotap2025.vistas.Cliente;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCell extends TableCell<ClienteDAO, String> {

    private Button btnCell;
    private String strLabelBtn;

    public ButtonCell(String label) {
        strLabelBtn = label;
        btnCell = new Button(strLabelBtn);
        btnCell.setOnAction(e -> {
            ClienteDAO objC = this.getTableView().getItems().get(this.getIndex());
            if(strLabelBtn.equals("Editar")) {
                new Cliente(this.getTableView(), objC);
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMACION");
                alert.setContentText("¿Desea eliminar el registro?");
                Optional<ButtonType> result = alert.showAndWait();
                if( result.get() == ButtonType.OK) {
                    objC.deleteCliente();
                }
            }
            this.getTableView().setItems(objC.selectCliente());
            this.getTableView().refresh();
        });
    }

    @Override
    protected void updateItem(String items, boolean empty) {
        super.updateItem(items, empty);
        if (!empty) {
            this.setGraphic(btnCell);
        }
    }
}
