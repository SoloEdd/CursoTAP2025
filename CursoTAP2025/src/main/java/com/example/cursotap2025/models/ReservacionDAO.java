package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class ReservacionDAO {

    private int id_reservacion;
    private int id_cliente;
    private int no_personas;
    private Timestamp fecha_reservacion;
    private int no_mesa;

    public void insertReservacionAdmin() {
        String query = "INSERT INTO reservacion (id_cliente, no_personas, fecha_reservacion) VALUES (?,?,?)";
        try{
            PreparedStatement pt = DbConnection.connection.prepareStatement(query);
            pt.setInt(1, id_cliente);
            pt.setInt(2, no_personas);
            pt.setTimestamp(3, fecha_reservacion);
            pt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertReservacion() {
        String query = "INSERT INTO reservacion (id_cliente, no_personas, fecha_reservacion) VALUES (?,?,?)";
        try (PreparedStatement ps = DbConnection.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id_cliente);
            ps.setInt(2, no_personas);
            ps.setTimestamp(3, fecha_reservacion);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        this.id_reservacion = rs.getInt(1);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateReservacion() {
        String query = "UPDATE reservacion SET id_cliente =?, no_personas = ?, fecha_reservacion = ? WHERE id_cliente = " + id_reservacion;
        try{
            PreparedStatement pt = DbConnection.connection.prepareStatement(query);
            pt.setInt(1, id_cliente);
            pt.setInt(2, no_personas);
            pt.setTimestamp(3, fecha_reservacion);
            pt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteReservacion() {
        String query = "DELETE FROM reservacion WHERE id_reservacion = " + id_reservacion;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<ReservacionDAO> selectAllReservacion() {
        String query = "SELECT * FROM reservacion";
        ObservableList<ReservacionDAO> reservacion = FXCollections.observableArrayList();
        ReservacionDAO reservacionDAO;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                reservacionDAO = new ReservacionDAO();
                reservacionDAO.setId_reservacion(rs.getInt("id_reservacion"));
                reservacionDAO.setId_cliente(rs.getInt("id_cliente"));
                reservacionDAO.setNo_personas(rs.getInt("no_personas"));
                reservacionDAO.setFecha_reservacion(rs.getTimestamp("fecha_reservacion"));
                reservacion.add(reservacionDAO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reservacion;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getNo_personas() {
        return no_personas;
    }

    public void setNo_personas(int no_persona) {
        this.no_personas = no_persona;
    }

    public Timestamp getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(Timestamp fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }
}
