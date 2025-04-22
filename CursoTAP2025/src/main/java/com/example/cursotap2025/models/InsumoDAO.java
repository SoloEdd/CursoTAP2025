package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsumoDAO {

    private int id_insumo;
    private String nombre_insumo;
    private String descripcion_insumo;
    private int id_proveedor;

    public void insertInsumo() {
        String query = "INSERT INTO insumo(nombre_insumo, descripcion, id_provedor) " +
                "values(?,?,?)";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateInsumo() {
        String query = "UPDATE insumo SET nombre_insumo = ?, descripcion = ?, id_provedor = ? WHERE id_insumo = ?";
        try{
            PreparedStatement stmt = DbConnection.connection.prepareStatement(query);
            stmt.setString(1, nombre_insumo);
            stmt.setString(2, descripcion_insumo);
            stmt.setInt(3, id_proveedor);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteInsumo() {
        String query = "DELETE FROM insumo WHERE id_insumo = ?";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<InsumoDAO> selectInsumo() {
        String query = "SELECT * FROM insumo";
        ObservableList<InsumoDAO> insumos = FXCollections.observableArrayList();
        InsumoDAO objInsumo;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                objInsumo = new InsumoDAO();
                objInsumo.setId_insumo(rs.getInt("id_insumo"));
                objInsumo.setNombre_insumo(rs.getString("nombre_insumo"));
                objInsumo.setDescripcion_insumo(rs.getString("descripcion"));
                objInsumo.setId_proveedor(rs.getInt("id_provedor"));
                insumos.add(objInsumo);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return insumos;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public String getNombre_insumo() {
        return nombre_insumo;
    }

    public void setNombre_insumo(String nombre_insumo) {
        this.nombre_insumo = nombre_insumo;
    }

    public String getDescripcion_insumo() {
        return descripcion_insumo;
    }

    public void setDescripcion_insumo(String descripcion_insumo) {
        this.descripcion_insumo = descripcion_insumo;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
}
