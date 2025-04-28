package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class PuestoTrabajoDAO {

    private int id_puesto;
    private String nombre_puesto;
    private String descripcion;

    public void insertarPuestoTrabajo() {
        String query = "INSERT INTO puesto_trabajo(nombre_puesto,descripcion) VALUES('"+nombre_puesto+"','"+descripcion+"')";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePuestoTrabajo() {
        String query =  "UPDATE puesto_trabajo SET nombre_puesto='"+nombre_puesto+"',descripcion='"+descripcion+"' WHERE id_puesto= " + id_puesto;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deletePuestoTrabajo() {
        String query = "DELETE FROM puesto_trabajo WHERE id_puesto= " + id_puesto;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<PuestoTrabajoDAO> selectAllPuestoTrabajo() {
        String query = "SELECT * FROM puesto_trabajo";
        ObservableList<PuestoTrabajoDAO> listPuestoTrabajo = FXCollections.observableArrayList();
        PuestoTrabajoDAO objPuestoTrabajo;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                objPuestoTrabajo = new PuestoTrabajoDAO();
                objPuestoTrabajo.setId_puesto(rs.getInt("id_puesto"));
                objPuestoTrabajo.setNombre_puesto(rs.getString("nombre_puesto"));
                objPuestoTrabajo.setDescripcion(rs.getString("descripcion"));
                listPuestoTrabajo.add(objPuestoTrabajo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listPuestoTrabajo;
    }


    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getNombre_puesto() {
        return nombre_puesto;
    }

    public void setNombre_puesto(String nombre_puesto) {
        this.nombre_puesto = nombre_puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
