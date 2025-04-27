package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriaDAO {

    private int id_categoria;
    private String nombreCategoria;
    private String descripcionCategoria;

    public void insertCategoria() {
        String query = "INSERT INTO categoria (nombreCategoria, descripcionCategoria) VALUES ('"+nombreCategoria+"','"+descripcionCategoria+"')";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCategoria() {
        String query = "UPDATE categoria SET nombreCategoria='"+nombreCategoria+"', descripcionCategoria='"+descripcionCategoria+"' WHERE id_categoria="+id_categoria;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCategoria() {
        String query = "DELETE FROM categoria WHERE id_categoria="+id_categoria;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CategoriaDAO> selectCategoria() {
        String query = "SELECT * FROM categoria";
        ObservableList<CategoriaDAO> categorias = FXCollections.observableArrayList();
        CategoriaDAO objCategoria;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                objCategoria = new CategoriaDAO();
                objCategoria.setId_categoria(rs.getInt("id_categoria"));
                objCategoria.setNombreCategoria(rs.getString("nombreCategoria"));
                objCategoria.setDescripcionCategoria(rs.getString("descripcionCategoria"));
                categorias.add(objCategoria);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public CategoriaDAO buscarCategoriaPorId(int id) {
        CategoriaDAO categoria = null;
        String quey = "SELECT * FROM categoria WHERE id_categoria= ?";
        try{
            PreparedStatement pstmt = DbConnection.connection.prepareStatement(quey);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                categoria = new CategoriaDAO();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
                categoria.setDescripcionCategoria(rs.getString("descripcion_categoria"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }


    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
}
