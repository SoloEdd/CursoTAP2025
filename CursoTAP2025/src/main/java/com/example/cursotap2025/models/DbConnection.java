package com.example.cursotap2025.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static String DB = "restaurante";
    private static String User = "gerente";
    private static String Password = "123";
    private static String Host = "localhost";
    private static int Port = 3306;
    public static Connection connection;

    public static void createConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Host + ":" + Port + "/" + DB, User, Password);
            System.out.println("Connection Established!");
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
