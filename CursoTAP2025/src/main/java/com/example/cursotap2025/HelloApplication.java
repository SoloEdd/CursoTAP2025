package com.example.cursotap2025;

import com.example.cursotap2025.vistas.Calculadora;
import com.example.cursotap2025.vistas.VentasRestaurante;
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
    private MenuItem mitCalculadora, mitRestaurate;
    private Scene scena;
    //private String teclas[] = {"7","8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "=", "0", ".", "-"};

    void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitRestaurate = new MenuItem("Restaurat");
        mitCalculadora.setOnAction(e -> {new Calculadora();});
        mitRestaurate.setOnAction(actionEvent -> new VentasRestaurante());
        menuCompetencia1 = new Menu("Competencia Uno");
        menuCompetencia1.getItems().addAll(mitCalculadora, mitRestaurate);
        menuBarPrincipal = new MenuBar();
        menuCompetencia2 = new Menu("Competencia Dos");
        menuBarPrincipal.getMenus().addAll(menuCompetencia1, menuCompetencia2);
        vBox = new VBox(menuBarPrincipal);
        scena = new Scene(vBox);
        scena.getStylesheets().add(getClass().getResource("/Styles/main.css").toExternalForm());
    }

    @Override
    public void start(Stage stage) throws IOException {
        CrearUI();
        stage.setTitle("Topicos Avanzandos de Programacion 2025");
        stage.setScene(scena);
        stage.show();
        stage.setMaximized(true);

    }

    //el padding aplica un espacidado del padre al nodo hijo
    //el spacing aplica un espcaiado del nodo hijo al padre
    public static void main(String[] args) {
        launch();
    }
}