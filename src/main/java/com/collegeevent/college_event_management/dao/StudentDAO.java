package com.collegeevent.college_event_management.dao;

import com.collegeevent.college_event_management.model.Student;
import com.collegeevent.college_event_management.util.DBUtil;

import java.sql.*;


public class StudentDAO {

    // =============== CREATE A NEW STUDENT RECORD ===============
    public int createStudent(Student s) {

        String sql = "INSERT INTO Student (user_id, name, email, department, year) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, s.getUserId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getDepartment());
            ps.setInt(5, s.getYear());

            int affected = ps.executeUpdate();

            if (affected == 0) {
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    s.setStudentId(id);
                    return id;  // return newly generated student_id
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error in createStudent: " + e.getMessage());
        }

        return -1;
    }

    // =============== GET STUDENT BY USER ID (Used After Login) ===============
    public Student findByUserId(int userId) {
        String sql = "SELECT * FROM Student WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setStudentId(rs.getInt("student_id"));
                    s.setUserId(rs.getInt("user_id"));
                    s.setName(rs.getString("name"));
                    s.setEmail(rs.getString("email"));
                    s.setDepartment(rs.getString("department"));
                    s.setYear(rs.getInt("year"));
                    return s;
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error in findByUserId: " + e.getMessage());
        }

        return null;
    }
}