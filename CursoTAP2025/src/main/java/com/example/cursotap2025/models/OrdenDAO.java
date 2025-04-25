package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class OrdenDAO {

    private int id_orden;
    private int idCte;
    private String emailCliente;
    private int no_mesa;
    private int id_empleado;
    private String nombreEmpleado;
    private Timestamp fecha;


    public void insertOrden() {
        String query = "INSERT INTO orden(idCte, no_mesa, id_empleado, fecha) VALUES (?,?,?,?)";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOrden() {
        String query = "UPDATE orden SET idCte = ?, no_mesa = ?, id_empleado = ?, fecha = ? WHERE idOrden = " + id_orden;
        try{
            PreparedStatement stmt = DbConnection.connection.prepareStatement(query);
            stmt.setInt(1, idCte);
            stmt.setInt(2, no_mesa);
            stmt.setInt(3, id_empleado);
            stmt.setTimestamp(4, fecha);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOrden() {
        String query = "DELETE FROM orden WHERE idOrden = " + id_orden;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<OrdenDAO> selectOrden() {
        OrdenDAO objOrden;
        ObservableList<OrdenDAO> listOrden = FXCollections.observableArrayList();
        String query = "SELECT o.id_orden, o.idCte, c.emailCte, o.no_mesa, o.id_empleado, " +
                "e.nombre AS nombreEmpleado, o.fecha " +
                "FROM orden o " +
                "JOIN cliente c ON o.idCte = c.idCte " +
                "JOIN empleado e ON o.id_empleado = e.id_empleado";

        try {
            PreparedStatement ps = DbConnection.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objOrden = new OrdenDAO();
                objOrden.setId_orden(rs.getInt("id_orden"));
                objOrden.setIdCte(rs.getInt("idCte"));
                objOrden.setEmailCliente(rs.getString("emailCte"));
                objOrden.setNo_mesa(rs.getInt("no_mesa"));
                objOrden.setId_empleado(rs.getInt("id_empleado"));
                objOrden.setNombreEmpleado(rs.getString("nombreEmpleado"));
                objOrden.setFecha(rs.getTimestamp("fecha"));
                listOrden.add(objOrden);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listOrden;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getIdCte() {
        return idCte;
    }

    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public int getNo_mesa() {
        return no_mesa;
    }

    public void setNo_mesa(int no_mesa) {
        this.no_mesa = no_mesa;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
