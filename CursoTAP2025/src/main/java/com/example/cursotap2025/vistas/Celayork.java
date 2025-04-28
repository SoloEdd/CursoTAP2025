package com.example.cursotap2025.vistas;

import com.example.cursotap2025.componentes.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Celayork extends Stage {

    private VBox vbox;
    private GridPane grid;
    private Button btn;
    private Label[] lblRutas;
    private ProgressBar[] pgbRutas;
    private Scene scene;
    private String[] strRutas = {"Pinos", "Teneria", "San Juan de la Vega", "Monte Blanco", "Laureles"};
    private Hilo[] thrRutas;
    

    public Celayork() {
        CrearUI();
        this.setTitle("Calles de Celayork");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI(){
        btn = new Button("Iniciar");
        pgbRutas = new ProgressBar[5];
        lblRutas = new Label[5];
        thrRutas = new Hilo[5];
        grid = new GridPane();
        for (int i = 0; i < pgbRutas.length; i++) {
            lblRutas[i] = new Label(strRutas[i]);
            pgbRutas[i] = new ProgressBar(0);
            thrRutas[i] = new Hilo(strRutas[i], pgbRutas[i]);
            grid.add(lblRutas[i], 0, i);
            grid.add(pgbRutas[i], 1, i);
        }

        btn.setOnAction(e -> {
            for (int i = 0; i < pgbRutas.length; i++) {
                thrRutas[i].start();
                if(thrRutas[i].isAlive()){
                    
                }
            }
        });
        vbox = new VBox(grid, btn);
        scene = new Scene(vbox, 300,200);
    }

}
