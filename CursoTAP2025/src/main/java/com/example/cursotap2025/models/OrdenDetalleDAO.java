package com.example.cursotap2025.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdenDetalleDAO {
    private int id_orden;
    private int id_producto;
    private int cantidad_producto;
    private double monto;

    public boolean insertDetalle() {
        String query = "INSERT INTO orden_detalle (id_orden, id_producto, cantidad_producto, monto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DbConnection.connection.prepareStatement(query)) {
            ps.setInt(1, id_orden);
            ps.setInt(2, id_producto);
            ps.setInt(3, cantidad_producto);
            ps.setDouble(4, monto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
