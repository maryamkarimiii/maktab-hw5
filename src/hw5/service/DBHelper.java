package hw5.service;

import java.sql.*;

public class DBHelper {
    private static final String URL = "jdbc:postgresql://localhost/hw5";
    private static final String USER = "postgres";
    private static final String PASSWORD = "4600099941";
    private Connection connection;

    public Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
