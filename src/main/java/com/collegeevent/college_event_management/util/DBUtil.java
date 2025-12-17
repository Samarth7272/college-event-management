package com.collegeevent.college_event_management.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/college_event_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Shiivam@68"; // change

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed: " + e.getMessage());
            return null;
        }
    }
}
