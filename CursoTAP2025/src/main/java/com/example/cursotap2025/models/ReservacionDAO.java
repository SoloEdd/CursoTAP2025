package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class ReservacionDAO {

    private int id_reservacion;
    private int id_cliente;
    private int no_persona;
    private Timestamp fecha_reservacion;

    public void insertReservacion() {
        String query = "INSERT INTO reservacion (id_cliente, no_persona, fecha_reservacion) VALUES (?,?,?)";
        try{
            PreparedStatement pt = DbConnection.connection.prepareStatement(query);
            pt.setInt(1, id_cliente);
            pt.setInt(2, no_persona);
            pt.setTimestamp(3, fecha_reservacion);
            pt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateReservacion() {
        String query = "UPDATE reservacion SET id_cliente =?, no_persona = ?, fecha_reservacion = ? WHERE id_cliente = " + id_reservacion;
        try{
            PreparedStatement pt = DbConnection.connection.prepareStatement(query);
            pt.setInt(1, id_cliente);
            pt.setInt(2, no_persona);
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
                reservacionDAO.setNo_persona(rs.getInt("no_persona"));
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

    public int getNo_persona() {
        return no_persona;
    }

    public void setNo_persona(int no_persona) {
        this.no_persona = no_persona;
    }

    public Timestamp getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(Timestamp fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }
}
