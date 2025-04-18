package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cliente extends Stage {

    private Button btnGuardar;
    private TextField txtNomCte,txtTelCte, txtDireccion, txtEmail;
    private VBox vBox;
    private Scene escena;
    private ClienteDAO objC;
    private TableView<ClienteDAO> tbvClientes;

    public Cliente(TableView<ClienteDAO> tbvCte, ClienteDAO obj){
        this.tbvClientes = tbvCte;
        CrearUI();
        if(obj == null){
            objC = new ClienteDAO();
        }else {
            objC = obj;
            txtNomCte.setText(objC.getNomCte());
            txtTelCte.setText(objC.getTelCte());
            txtDireccion.setText(objC.getDireccion());
            txtEmail.setText(objC.getEmailCte());
        }
        this.setTitle("Registrar Cliente");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        txtNomCte = new TextField();
        txtNomCte.setPromptText("Nombre");
        txtTelCte = new TextField();
        txtTelCte.setPromptText("Telefono");
        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            objC.setNomCte(txtNomCte.getText());
            objC.setTelCte(txtTelCte.getText());
            objC.setDireccion(txtDireccion.getText());
            objC.setEmailCte(txtEmail.getText());
            if(objC.getIdCliente() > 0){
                objC.updateCliente();
            }else {
                objC.insertCliente();
            }
            tbvClientes.setItems(objC.selectCliente());
            tbvClientes.refresh();
            this.close();
        });
        vBox = new VBox(txtNomCte,txtDireccion,txtTelCte,txtEmail,btnGuardar);
        escena = new Scene(vBox,600,600);
    }


}
