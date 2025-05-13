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
    String strTeclas[] = {"7","8","9","*","4","5","6","/","1","2","3","+","=","0",".","-"};
    private String operador = "";
    private String primerOperando = "";
    private boolean nuevoIngreso = false;
    private boolean puntoPresente = false;
    private boolean operadorPresente = false;
    private Button btnBorrar;

    public Calculadora() {
        CrearUI();
        this.setScene(scene);
        this.setTitle("Calculadora");
        this.show();
        this.setResizable(false);
    }

    private void CrearUI(){
        CreateKeyboard();
        txtDisplay = new TextField();
        txtDisplay.setPromptText("Digite un valor");
        txtDisplay.setEditable(false);
        txtDisplay.setAlignment(Pos.BASELINE_RIGHT);
        txtDisplay.setPadding(new Insets(0, 0, 10, 0));
        btnBorrar = new Button("Borrar");
        btnBorrar.setPrefSize(230, 10);
        btnBorrar.setOnAction(e -> {Borrar();});
        vbox = new VBox(txtDisplay, gdpTeclado, btnBorrar);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        scene = new Scene(vbox, 230,280);
    }

    public void CreateKeyboard(){
        arrBtnTeclado = new Button[4][4];
        gdpTeclado = new GridPane();
        gdpTeclado.setHgap(3);
        gdpTeclado.setVgap(3);
        int pos = 0;
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4; j++) {
                arrBtnTeclado[i][j] = new Button(strTeclas[pos]);
                int finalPos = pos;
                arrBtnTeclado[i][j].setOnAction(event -> EventoTeclado(strTeclas[finalPos]));
                arrBtnTeclado[i][j].setPrefSize(50,50);
                gdpTeclado.add(arrBtnTeclado[i][j], j, i);
                pos++;
            }
        }
    }

    private void EventoTeclado(String strTecla) {
        if (strTecla.matches("[0-9]")) {
            if (nuevoIngreso) {
                txtDisplay.setText(strTecla);
                nuevoIngreso = false;
            } else {
                txtDisplay.appendText(strTecla);
            }
        }
        else if (strTecla.equals(".") && !puntoPresente) {
            txtDisplay.appendText(strTecla);
            puntoPresente = true;
        }
        else if (strTecla.matches("[+\\-*/]") && !operadorPresente) {
            if (!txtDisplay.getText().isEmpty()) {
                primerOperando = txtDisplay.getText();
                operador = strTecla;
                nuevoIngreso = true;
                puntoPresente = false;
                operadorPresente = true;
                bloquearOperadores(true); // Bloquear otros operadores
            } else {
                txtDisplay.setText("Error: Ingrese un número");
            }
        }
        else if (strTecla.equals("=")) {
            if (!primerOperando.isEmpty() && !txtDisplay.getText().isEmpty() && !nuevoIngreso) {
                Calcular();
                operadorPresente = false;
                bloquearOperadores(false);
            } else {
                txtDisplay.setText("Error: Operación inválida");
            }
        }
    }

    private void bloquearOperadores(boolean bloquear) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String textoBoton = arrBtnTeclado[i][j].getText();
                if (textoBoton.matches("[+\\-*/]") && !textoBoton.equals(operador)) {
                    arrBtnTeclado[i][j].setDisable(bloquear);
                }
            }
        }
    }

    private void Calcular(){
        double resultado;
        try {
            double num1 = Double.parseDouble(primerOperando);
            double num2 = Double.parseDouble(txtDisplay.getText());
            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    txtDisplay.setText(String.valueOf(resultado));
                    break;
                case "-":
                    resultado = num1 - num2;
                    txtDisplay.setText(String.valueOf(resultado));
                    break;
                case "*":
                    resultado = num1 * num2;
                    txtDisplay.setText(String.valueOf(resultado));
                    break;
                case "/":
                    resultado = num1 / num2;
                    if(!(num2 == 0)){
                        txtDisplay.setText(String.valueOf(resultado));
                    }else {txtDisplay.setText("Error!! Denominador igual a 0");};
                    break;
                default:
                    txtDisplay.setText("Error: Operación no soportada");
            }
        } catch (NumberFormatException e) {
            txtDisplay.setText("Error: Número inválido");
        }
        nuevoIngreso = true;
    }

    private void Borrar() {
        txtDisplay.clear();
        primerOperando = "";
        operador = "";
        nuevoIngreso = false;
        puntoPresente = false;
        operadorPresente = false;
        bloquearOperadores(false);
    }
}