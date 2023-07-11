package com.example.carrentalapplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection = new DBConnection();
    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/carrental";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    public DBConnection() {

    }

    public static DBConnection getInstance() {
        return dbConnection;
    }

    public Connection getConnection() {
        try {
            if (connection == null)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


}



