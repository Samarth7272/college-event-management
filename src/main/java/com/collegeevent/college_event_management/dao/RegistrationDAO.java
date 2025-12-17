package com.collegeevent.college_event_management.dao;

import com.collegeevent.college_event_management.model.Registration;
import com.collegeevent.college_event_management.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    // ============ REGISTER STUDENT FOR AN EVENT ============
    public boolean registerStudentForEvent(int studentId, int eventId) {
        String sql = "INSERT INTO Registration (event_id, student_id) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, eventId);
            ps.setInt(2, studentId);

            int affected = ps.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            // This can fail if:
            // - event_id doesn't exist
            // - student_id doesn't exist
            // - UNIQUE(event_id, student_id) is violated (already registered)
            System.out.println("❌ Error in registerStudentForEvent: " + e.getMessage());
            return false;
        }
    }

    // ============ GET ALL REGISTRATIONS FOR A STUDENT ============
    public List<Registration> getRegistrationsByStudent(int studentId) {
        List<Registration> list = new ArrayList<>();

        String sql = "SELECT * FROM Registration WHERE student_id = ? ORDER BY registered_at DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Registration r = new Registration();
                    r.setRegistrationId(rs.getInt("registration_id"));
                    r.setEventId(rs.getInt("event_id"));
                    r.setStudentId(rs.getInt("student_id"));
                    r.setRegisteredAt(rs.getTimestamp("registered_at"));
                    r.setStatus(rs.getString("status"));
                    list.add(r);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error in getRegistrationsByStudent: " + e.getMessage());
        }

        return list;
    }
}
