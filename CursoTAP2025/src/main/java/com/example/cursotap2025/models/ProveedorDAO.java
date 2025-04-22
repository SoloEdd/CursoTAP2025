package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProveedorDAO {

    private int id_proveedor;
    private String nombre_proveedor;
    private String no_contacto;
    private String direccion;
    private String email;


    public void insertProveedor() {
        String query = "INSERT INTO  proveedor(nombre_proveedor, no_contacto, direccion, email) VALUES(?,?,?,?,?)";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProveedor() {
        String query = "UPDATE proveedor SET nombre_proveedor = '"+nombre_proveedor+"', no_contacto = '"+no_contacto+"', direccion = '"+direccion+"'," +
                "email = '"+email+"' WHERE id_proveedor = " + id_proveedor;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteProveedor() {
        String query = "DELETE FROM proveedor WHERE id_proveedor = " + id_proveedor;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<ProveedorDAO> selectProveedor() {
        String query = "SELECT * FROM proveedor";
        ObservableList<ProveedorDAO> listproveedor = FXCollections.observableArrayList();
        ProveedorDAO objproveedor;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                objproveedor = new ProveedorDAO();
                objproveedor.setId_proveedor(rs.getInt("id_proveedor"));
                objproveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                objproveedor.setNo_contacto(rs.getString("no_contacto"));
                objproveedor.setDireccion(rs.getString("direccion"));
                objproveedor.setEmail(rs.getString("email"));
                listproveedor.add(objproveedor);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listproveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getNo_contacto() {
        return no_contacto;
    }

    public void setNo_contacto(String no_contacto) {
        this.no_contacto = no_contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
