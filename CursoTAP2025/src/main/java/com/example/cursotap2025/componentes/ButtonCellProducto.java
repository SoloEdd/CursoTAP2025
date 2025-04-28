package com.example.cursotap2025.componentes;

import com.example.cursotap2025.models.ClienteDAO;
import com.example.cursotap2025.models.ProductoDAO;
import com.example.cursotap2025.vistas.Cliente;
import com.example.cursotap2025.vistas.Producto;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCellProducto extends TableCell<ProductoDAO, String> {

    private Button btnCell;
    private String strLabelBtn;

    public ButtonCellProducto(String label) {
        strLabelBtn = label;
        btnCell = new Button(strLabelBtn);
        btnCell.setOnAction(e -> {
            ProductoDAO objP = this.getTableView().getItems().get(this.getIndex());
            if(strLabelBtn.equals("Editar")) {
                new Producto(this.getTableView(), objP);
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMACION");
                alert.setContentText("¿Desea eliminar el registro?");
                Optional<ButtonType> result = alert.showAndWait();
                if( result.get() == ButtonType.OK) {
                    objP.deleteProducto();
                }
            }
            this.getTableView().setItems(objP.selectProducto());
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
