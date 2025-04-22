package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductoDAO {

    private int id_producto;
    private String nombre_producto;
    private double precio;
    private double costo;
    private int id_categoria;

    public void insertProducto() {
        String query = "INSERT INTO producto (nombre_producto, precio, costo, id_categoria) VALUES (?,?,?,?)";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProducto() {
        String query = "UPDATE producto SET nombre_producto=?, precio=?, costo=? , id_categoria = ? WHERE id_producto=?";
        try{
            PreparedStatement pstmt = DbConnection.connection.prepareStatement(query);
            pstmt.setString(1, nombre_producto);
            pstmt.setDouble(2, precio);
            pstmt.setDouble(3, costo);
            pstmt.setInt(4, id_categoria);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProductoDAO> selectProducto() {
        String query = "SELECT * FROM producto";
        ObservableList<ProductoDAO> productos = FXCollections.observableArrayList();
        ProductoDAO objProducto;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                objProducto = new ProductoDAO();
                objProducto.setId_producto(rs.getInt("id_producto"));
                objProducto.setNombre_producto(rs.getString("nombre_producto"));
                objProducto.setPrecio(rs.getDouble("precio"));
                objProducto.setCosto(rs.getDouble("costo"));
                objProducto.setId_categoria(rs.getInt("id_categoria"));
                productos.add(objProducto);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }


    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
