package com.pahanaedu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/pahana_edu_db?useSSL=false";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = "Rms74627"; 


    private DBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.", e);
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
            throw e;
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (dbConnection == null || dbConnection.getConnection().isClosed()) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
