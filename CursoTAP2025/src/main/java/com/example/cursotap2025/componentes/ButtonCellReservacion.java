package com.example.cursotap2025.componentes;

import com.example.cursotap2025.models.ReservacionDAO;
import com.example.cursotap2025.vistas.Reservacion;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCellReservacion extends TableCell<ReservacionDAO, String> {

    private Button btnCell;
    private String strLabelBtn;

    public ButtonCellReservacion(String label) {
        strLabelBtn = label;
        btnCell = new Button(strLabelBtn);
        btnCell.setOnAction(e -> {
            ReservacionDAO objReservacion = this.getTableView().getItems().get(this.getIndex());
            if(strLabelBtn.equals("Editar")) {
                new Reservacion(this.getTableView(), objReservacion);
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMACION");
                alert.setContentText("¿Desea eliminar el registro?");
                Optional<ButtonType> result = alert.showAndWait();
                if( result.get() == ButtonType.OK) {
                    objReservacion.deleteReservacion();
                }
            }
            this.getTableView().setItems(objReservacion.selectAllReservacion());
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
