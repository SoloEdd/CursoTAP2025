package com.example.cursotap2025.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    public boolean validarAdmin(String nombre, String password) {
        String query = "SELECT * FROM administrador WHERE nombre = ? AND password = ?";
        try (PreparedStatement stmt = DbConnection.connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // si hay algún resultado, las credenciales son válidas
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
