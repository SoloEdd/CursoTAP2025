package com.example.cursotap2025.vistas;

import com.example.cursotap2025.componentes.ButtonCell;
import com.example.cursotap2025.models.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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
        tbvClientes = new TableView<>();
        btnAgregar = new Button();
        btnAgregar.setOnAction( actionEvent -> new Cliente(tbvClientes, null));
        ImageView imv = new ImageView(getClass().getResource("/images/person_add_icon.png").toString());
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        btnAgregar.setGraphic(imv);
        tlbMenu = new ToolBar(btnAgregar);
        CreateTable();
        vBox = new VBox(tlbMenu,tbvClientes);
        escena = new Scene(vBox, 800, 600);
    }

    private void CreateTable() {

        ClienteDAO obC = new ClienteDAO();
        TableColumn<ClienteDAO,String> tbcNomCte = new TableColumn<>("Nombre");
        tbcNomCte.setCellValueFactory(new PropertyValueFactory<>("nomCte"));
        TableColumn<ClienteDAO,String> tbcTel = new TableColumn<>("Telefono");
        tbcTel.setCellValueFactory(new PropertyValueFactory<>("telCte"));
        TableColumn<ClienteDAO,String> tbcDireccion = new TableColumn<>("Dirección");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn<ClienteDAO,String> tbcEmail = new TableColumn<>("Email");
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("emailCte"));
        TableColumn<ClienteDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<ClienteDAO, String>, TableCell<ClienteDAO, String>>() {
            @Override
            public TableCell<ClienteDAO, String> call(TableColumn<ClienteDAO, String> param) {
                return new ButtonCell("Editar");
            }
        });
        TableColumn<ClienteDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<ClienteDAO, String>, TableCell<ClienteDAO, String>>() {
            @Override
            public TableCell<ClienteDAO, String> call(TableColumn<ClienteDAO, String> param) {
                return new ButtonCell("Eliminar");
            }
        });
        tbvClientes.getColumns().addAll(tbcNomCte,tbcDireccion,tbcTel,tbcEmail, tbcEditar,tbcEliminar);
        tbvClientes.setItems(obC.selectCliente());

    }
}
