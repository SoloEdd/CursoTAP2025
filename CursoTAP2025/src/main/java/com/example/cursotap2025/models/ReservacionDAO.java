package com.example.cursotap2025.models;

import java.sql.PreparedStatement;
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


}
