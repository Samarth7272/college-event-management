package com.collegeevent.college_event_management.dao;

import com.collegeevent.college_event_management.model.UserAccount;
import com.collegeevent.college_event_management.util.DBUtil;

import java.sql.*;

public class UserAccountDAO {

    // Method to create a student user (used during registration)
    public int createStudentUser(String username, String password) {
        String sql = "INSERT INTO UserAccount (username, password_hash, role) VALUES (?, ?, 'student')";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, username);
            ps.setString(2, password);  // Later we will add hashing

            int affected = ps.executeUpdate();

            if (affected == 0) {
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Returning new user_id
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error in createStudentUser: " + e.getMessage());
        }

        return -1;
    }

    // Login lookup method
    public UserAccount findByUsername(String username) {
        String sql = "SELECT * FROM UserAccount WHERE username = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserAccount user = new UserAccount();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPasswordHash(rs.getString("password_hash"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error in findByUsername: " + e.getMessage());
        }

        return null;
    }
}