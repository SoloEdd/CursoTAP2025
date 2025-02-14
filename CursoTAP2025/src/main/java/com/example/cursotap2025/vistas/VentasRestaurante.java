package com.example.cursotap2025.vistas;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class VentasRestaurante extends Stage {

    private Panel panel;
    private Scene scene;


    public VentasRestaurante() {
        CrearUI();
        this.setTitle("Restaurante mmlon");
        this.setScene(scene);
        this.setMaximized(true);
        this.show();

    }

    private void CrearUI() {

        panel = new Panel("Restaurante");
        panel.getStyleClass().add("panel-primary");
        scene = new Scene(panel);
    }

}
