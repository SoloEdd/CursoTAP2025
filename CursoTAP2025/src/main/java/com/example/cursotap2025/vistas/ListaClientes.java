package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaClientes extends Stage {

    private ToolBar tlbMenu;
    private TableView<ClienteDAO> tbvClientes;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;

    public ListaClientes(){
        CrearUI();
        this.setTitle("Listado de Clientes :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        btnAgregar = new Button();
        ImageView imv = new ImageView(getClass().getResource("/images/person_add_icon.png").toString());
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        btnAgregar.setGraphic(imv);
        tlbMenu = new ToolBar(btnAgregar);
        tbvClientes = new TableView<>();
        CreateTable();
        vBox = new VBox(tlbMenu,tbvClientes);
        escena = new Scene(vBox, 800, 600);
    }

    private void CreateTable() {

        TableColumn<ClienteDAO,String> tbcNomCte = new TableColumn<>("Nombre");
        tbcNomCte.setCellValueFactory(new PropertyValueFactory<>("nomCte"));
        TableColumn<ClienteDAO,String> tbcDireccion = new TableColumn<>("Dirección");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn<ClienteDAO,String> tbcTel = new TableColumn<>("Telefono");
        tbcTel.setCellValueFactory(new PropertyValueFactory<>("telCte"));
        TableColumn<ClienteDAO,String> tbcEmail = new TableColumn<>("Email");
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("emailCte"));

    }
}
