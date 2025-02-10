package com.example.cursotap2025;

import com.example.cursotap2025.vistas.Calculadora;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private VBox vBox;
    private MenuBar menuBarPrincipal;
    private Menu menuCompetencia1, menuCompetencia2;
    private MenuItem mitCalculadora;
    private Scene scena;
    private String teclas[] = {"7","8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "=", "0", ".", "-"};

    void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(e -> {new Calculadora();});
        menuCompetencia1 = new Menu("Competencia Uno");
        menuCompetencia1.getItems().addAll(mitCalculadora);
        menuBarPrincipal = new MenuBar();
        menuCompetencia2 = new Menu("Competencia Dos");
        menuBarPrincipal.getMenus().addAll(menuCompetencia1, menuCompetencia2);
        vBox = new VBox(menuBarPrincipal);

    }

    @Override
    public void start(Stage stage) throws IOException {
        CrearUI();
        stage.setTitle("Titulo de la ventana a ver que tal juasjuasjuas");
        stage.setScene(new Scene(vBox));
        stage.show();
        stage.setMaximized(true);
    }

    //el padding aplica un espacidado del padre al nodo hijo
    //el spacing aplica un espcaiado del nodo hijo al padre
    public static void main(String[] args) {
        launch();
    }
}