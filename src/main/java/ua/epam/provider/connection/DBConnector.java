package ua.epam.provider.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ua.epam.provider.connection.ConnectionConstants.*;

public class DBConnector {
    private Connection connection;


    public Connection DBConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Successfully connected to database.");
            } else
                System.out.println("Failed to connect to database.");
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        return connection;
    }

    public static Connection buildConnection() {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.DBConnection();
        return connection;
    }

    public static void Close(Connection connection) {
        try {
            connection.close();
            System.out.println("Connection close");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}
