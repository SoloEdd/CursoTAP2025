package com.example.cursotap2025.componentes;

import com.example.cursotap2025.models.CategoriaDAO;
import com.example.cursotap2025.models.ProductoDAO;
import com.example.cursotap2025.vistas.Categoria;
import com.example.cursotap2025.vistas.Producto;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCellCategoria extends TableCell<CategoriaDAO, String> {

    private Button btnCell;
    private String strLabelBtn;

    public ButtonCellCategoria(String label) {
        strLabelBtn = label;
        btnCell = new Button(strLabelBtn);
        btnCell.setOnAction(e -> {
            CategoriaDAO objC = this.getTableView().getItems().get(this.getIndex());
            if(strLabelBtn.equals("Editar")) {
                new Categoria(this.getTableView(), objC);
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMACION");
                alert.setContentText("¿Desea eliminar el registro?");
                Optional<ButtonType> result = alert.showAndWait();
                if( result.get() == ButtonType.OK) {
                    objC.deleteCategoria();
                }
            }
            this.getTableView().setItems(objC.selectCategoria());
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
