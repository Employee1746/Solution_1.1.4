package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/users";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "g23LHmVqZYZ8";

    public static Connection getMySQLConnection() throws SQLException {
        return getMySQLConnection(URL, USERNAME, PASSWORD);
    }

    public static Connection getMySQLConnection(String url, String userName, String password) throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
