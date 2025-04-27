package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoDAO {

    private int id_producto;
    private String nombre_producto;
    private double precio;
    private double costo;
    private int id_categoria;
    private int totalVendido;
    private double ingresosTotales;
    private String imagenPath;

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

    public void deleteProducto() {
        String query = "DELETE FROM producto WHERE id_producto=" + id_producto;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
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

    public static ObservableList<ProductoDAO> obtenerProductosMasVendidos() {
        String query = """
        SELECT p.*, 
               SUM(od.cantidad_producto) as total_vendido,
               SUM(od.monto) as ingresos_totales
        FROM producto p
        JOIN orden_detalle od ON p.id_producto = od.id_producto
        GROUP BY p.id_producto
        ORDER BY total_vendido DESC
    """;
        ObservableList<ProductoDAO> productos = FXCollections.observableArrayList();
        try {
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ProductoDAO producto = new ProductoDAO();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre_producto(rs.getString("nombre_producto"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCosto(rs.getDouble("costo"));
                producto.setId_categoria(rs.getInt("id_categoria"));
                // Agregamos campos temporales para el reporte
                producto.setTotalVendido(rs.getInt("total_vendido"));
                producto.setIngresosTotales(rs.getDouble("ingresos_totales"));
                productos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    public static ObservableList<ProductoDAO> obtenerProductosPorCategoria(int idCategoria) {
        String query = "SELECT * FROM producto WHERE id_categoria = ?";
        ObservableList<ProductoDAO> productos = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = DbConnection.connection.prepareStatement(query);
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductoDAO producto = new ProductoDAO();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre_producto(rs.getString("nombre_producto"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCosto(rs.getDouble("costo"));
                producto.setId_categoria(rs.getInt("id_categoria"));
                producto.setImagenPath(rs.getString("imagen_path"));
                productos.add(producto);
            }
        } catch (SQLException e) {
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

    public int getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(int totalVendido) {
        this.totalVendido = totalVendido;
    }

    public double getIngresosTotales() {
        return ingresosTotales;
    }

    public void setIngresosTotales(double ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }
}
