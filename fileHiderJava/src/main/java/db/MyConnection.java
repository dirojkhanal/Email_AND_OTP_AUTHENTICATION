package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private static Connection connection;

    // Method to establish and return the connection
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hidefile?useSSL=false",
                        "root",
                        "Admin123@"
                );
                System.out.println(" Connection established successfully!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Method to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println(" Connection closed successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        getConnection();   // Establish connection
        closeConnection(); // Close connection
    }
}
