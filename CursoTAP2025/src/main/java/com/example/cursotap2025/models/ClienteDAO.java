package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAO {

    private int idCliente;
    private String nomCte;
    private String telCte;
    private String direccion;
    private String emailCte;
    private String password;

    public void insertClienteAdmin() {
        String query = "INSERT INTO cliente(nomCte, telCte, direccion, emailCte) " +
                "values('"+nomCte+"','"+telCte+"','"+direccion+"','"+emailCte+"')";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertCliente() {
        String query = "INSERT INTO cliente(nomCte, telCte, direccion, emailCte) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DbConnection.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nomCte);
            ps.setString(2, telCte);
            ps.setString(3, direccion);
            ps.setString(4, emailCte);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        this.idCliente = rs.getInt(1);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        String query = "SELECT * FROM cliente";
        ObservableList<ClienteDAO> listaC = FXCollections.observableArrayList();
        ClienteDAO objC;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new ClienteDAO();
                objC.setIdCliente(res.getInt("idCte"));
                objC.setNomCte(res.getString("nomCte"));
                objC.setTelCte(res.getString("telCte"));
                objC.setDireccion(res.getString("direccion"));
                objC.setEmailCte(res.getString("emailCte"));
                listaC.add(objC);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaC;
    }

    public boolean login(String email, String password) {
        String query = "SELECT * FROM cliente WHERE emailCte = ? AND password = ?";
        try {
            PreparedStatement stmt = DbConnection.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Si hay resultado, es válido
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return nomCte + " - " + telCte;
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
