package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAO {

    private int idCliente;
    private String nomCte;
    private String telCte;
    private String direccion;
    private String emailCte;

    public void insertCliente() {
        String query = "insert into cliente(nomCte, telCte, direccion, emailCte) " +
                "values(?,?,?,?)";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCliente() {
        String query = "UPDATE cliente SET nomCte = '"+nomCte+"'," +
                "telCte = '"+telCte+"',direccion = '"+direccion+"'," +
                "emailCte = '"+emailCte+"' WHERE idCte = "+idCliente;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteCliente() {
        String query = "DELETE FROM clientes WHERE idCte = "+idCliente;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<ClienteDAO> selectCliente() {
        String query = "SELECT * FROM clientes";
        ObservableList<ClienteDAO> listaC = FXCollections.observableArrayList();
        ClienteDAO objC;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new ClienteDAO();
                objC.setIdCliente(res.getInt("idCte"));
                objC.setNomCte(res.getString("nomCte"));
                objC.setDireccion(res.getString("direccion"));
                objC.setTelCte(res.getString("telCte"));
                objC.setEmailCte(res.getString("emailCte"));
                listaC.add(objC);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaC;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomCte() {
        return nomCte;
    }

    public void setNomCte(String nomCte) {
        this.nomCte = nomCte;
    }

    public String getTelCte() {
        return telCte;
    }

    public void setTelCte(String telCte) {
        this.telCte = telCte;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailCte() {
        return emailCte;
    }

    public void setEmailCte(String emailCte) {
        this.emailCte = emailCte;
    }
}

//Si se requiere refrescar el comportamiento de un componente (objeto) ya creado hay que bucar la
//referencia a la instancia
