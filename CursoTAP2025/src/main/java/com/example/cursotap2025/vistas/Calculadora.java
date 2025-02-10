package com.example.cursotap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Scene scene;
    private TextField txtDisplay;
    private VBox vbox;
    private GridPane gdpTeclado;
    private Button[][] arrBtnTeclado;

    public Calculadora() {
        CrearUI();
        this.setScene(scene);
        this.setTitle("Calculadora");
        this.show();
    }

    private void CrearUI(){
        CreateKeyboard();
        txtDisplay = new TextField("0");
        //txtDisplay.setPromptText("Digite un valor");
        txtDisplay.setEditable(false);
        txtDisplay.setAlignment(Pos.BASELINE_RIGHT);
        txtDisplay.setPadding(new Insets(0, 0, 10, 0));
        vbox = new VBox(txtDisplay, gdpTeclado);
        //vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        scene = new Scene(vbox, 400,600);
    }

    public void CreateKeyboard(){
        arrBtnTeclado = new Button[4][4];
        gdpTeclado = new GridPane();
        gdpTeclado.setHgap(10);
        gdpTeclado.setVgap(10);
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4; j++) {
                arrBtnTeclado[i][j] = new Button("#");
                int finalPos = pos;
                arrBtnTeclado[i][j].setOnAction(event -> EventoTeclado(strTaclas[FinalPos]));
                arrBtnTeclado[i][j].setPrefSize(50,50);
                gdpTeclado.add(arrBtnTeclado[i][j], j, i);
                pos++;
            }
        }
    }

    private void EventoTeclado(String strTacla){
        txtDisplay.appendText(strTacla);
    }

}
