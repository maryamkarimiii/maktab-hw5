package hw5.service;

import java.sql.Connection;

public class ApplicationConstant {
    private static Connection connection = new DBHelper().connect();

    public static Connection getConnection() {
        return connection;
    }
}
