package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/cafe_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";  // Change this to your MySQL password

    // Method to get database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC Driver not found.");
            System.out.println("Please add mysql-connector-java to your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Unable to connect to database.");
            System.out.println("Please check your database credentials and ensure MySQL is running.");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to close connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: Unable to close database connection.");
                e.printStackTrace();
            }
        }
    }
}
