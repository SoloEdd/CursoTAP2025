package com.example.cursotap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadoDAO {

    private int id_empleado;
    private String primer_apellido;
    private String segundo_apellido;
    private String nombre;
    private String curp;
    private String rfc;
    private double sueldo;
    private String celEmp;
    private String nssEmp;
    private String horario;
    private String fecha_ingreso;
    private int id_puesto;

    
    public void insertarEmpleado() {
        String query = "INSERT INTO empleado(primer_apellido, segundo_apellido, nombre, curp, rfc, sueldo, celEmp " +
                "nssEmp, horario, fecha_ingreso, id_puesto) VALUES (?,?,?,?,?,?,?,?,?)";   
        try{
            Statement stmt = DbConnection.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateEmpleado() {
        String query = "UPDATE empleado SET primer_apellido = ?, segundo_apellido = ?, nombre = ?, curp = ?, rfc = ?, sueldo = ?, celEmp = ?, nssEmp = ?, horario = ?, fecha_ingreso = ?, id_puesto = ? WHERE id_empleado = ?";
        try {
            PreparedStatement ps = DbConnection.connection.prepareStatement(query);
            ps.setString(1, primer_apellido);
            ps.setString(2, segundo_apellido);
            ps.setString(3, nombre);
            ps.setString(4, curp);
            ps.setString(5, rfc);
            ps.setDouble(6, sueldo);
            ps.setString(7, celEmp);
            ps.setString(8, nssEmp);
            ps.setString(9, horario);
            ps.setString(10, fecha_ingreso);
            ps.setInt(11, id_puesto);
            ps.setInt(12, id_empleado);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmpleado() {
        String query = "DELETE FROM empleado WHERE id_empleado = ?";
        try {
            PreparedStatement ps = DbConnection.connection.prepareStatement(query);
            ps.setInt(1, id_empleado);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<EmpleadoDAO> selectEmpleado(){
        String query = "SELECT * FROM empleado";
        ObservableList<EmpleadoDAO> listaEmpleado = FXCollections.observableArrayList();
        EmpleadoDAO objEmpleado;
        try{
            Statement stmt = DbConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                objEmpleado = new EmpleadoDAO();
                objEmpleado.setId_empleado(rs.getInt("id_empleado"));
                objEmpleado.setPrimer_apellido(rs.getString("primer_apellido"));
                objEmpleado.setSegundo_apellido(rs.getString("segundo_apellido"));
                objEmpleado.setNombre(rs.getString("nombre"));
                objEmpleado.setCurp(rs.getString("curp"));
                objEmpleado.setRfc(rs.getString("rfc"));
                objEmpleado.setSueldo(rs.getDouble("sueldo"));
                objEmpleado.setCelEmp(rs.getString("celEmp"));
                objEmpleado.setNssEmp(rs.getString("nssEmp"));
                objEmpleado.setHorario(rs.getString("horario"));
                objEmpleado.setFecha_ingreso(rs.getString("fecha_ingreso"));
                objEmpleado.setId_puesto(rs.getInt("id_puesto"));
                listaEmpleado.add(objEmpleado);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaEmpleado;
    }


    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getCelEmp() {
        return celEmp;
    }

    public void setCelEmp(String celEmp) {
        this.celEmp = celEmp;
    }

    public String getNssEmp() {
        return nssEmp;
    }

    public void setNssEmp(String nssEmp) {
        this.nssEmp = nssEmp;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
}
