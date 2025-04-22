package com.example.cursotap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class MesaDAO {

    private int no_mesa;
    private int capacidad;

    public void insertMesa() {
        String query = "insert into mesa values(?)";
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMesa() {
        String query = "update mesa set capacidad = ? where no_mesa = " + no_mesa;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMesa() {
        String query = "delete from mesa where no_mesa = " + no_mesa;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<MesaDAO> selectMesa() {
        String query = "select * from mesa";
        ObservableList<MesaDAO> listMesas = FXCollections.observableArrayList();
        MesaDAO objMesa;
        try {
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                objMesa = new MesaDAO();
                objMesa.setNo_mesa(rs.getInt("no_mesa"));
                objMesa.setCapacidad(rs.getInt("capacidad"));
                listMesas.add(objMesa);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listMesas;
    }

    public int getNo_mesa() {
        return no_mesa;
    }

    public void setNo_mesa(int no_mesa) {
        this.no_mesa = no_mesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
