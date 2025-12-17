package com.collegeevent.college_event_management.dao;

import com.collegeevent.college_event_management.model.Event;
import com.collegeevent.college_event_management.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    // ======= Get all events (used in Main: listEvents) =======
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        String sql = "SELECT * FROM Event ORDER BY event_date, start_time";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Event e = new Event();
                e.setEventId(rs.getInt("event_id"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));
                e.setOrganizerId((Integer) rs.getObject("organizer_id"));
                e.setVenueId((Integer) rs.getObject("venue_id"));
                e.setEventDate(rs.getDate("event_date"));
                e.setStartTime(rs.getTime("start_time"));
                e.setEndTime(rs.getTime("end_time"));
                e.setSeatsAvailable(rs.getInt("seats_available"));
                events.add(e);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error in getAllEvents: " + e.getMessage());
        }

        return events;
    }

    // ======= Optional: get a single event by ID (useful later) =======
    public Event getEventById(int eventId) {
        String sql = "SELECT * FROM Event WHERE event_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, eventId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Event e = new Event();
                    e.setEventId(rs.getInt("event_id"));
                    e.setTitle(rs.getString("title"));
                    e.setDescription(rs.getString("description"));
                    e.setOrganizerId((Integer) rs.getObject("organizer_id"));
                    e.setVenueId((Integer) rs.getObject("venue_id"));
                    e.setEventDate(rs.getDate("event_date"));
                    e.setStartTime(rs.getTime("start_time"));
                    e.setEndTime(rs.getTime("end_time"));
                    e.setSeatsAvailable(rs.getInt("seats_available"));
                    return e;
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error in getEventById: " + e.getMessage());
        }

        return null;
    }
}
