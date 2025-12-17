package com.collegeevent.college_event_management.model;

import java.sql.Timestamp;

public class Registration {

    private int registrationId;
    private int eventId;
    private int studentId;
    private Timestamp registeredAt;
    private String status; // registered / cancelled

    public Registration() {}

    // ---------- GETTERS ----------
    public int getRegistrationId() {
        return registrationId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getStudentId() {
        return studentId;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public String getStatus() {
        return status;
    }

    // ---------- SETTERS ----------
    public void setRegistrationId(int registrationId) {   // <<< missing method added
        this.registrationId = registrationId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId=" + registrationId +
                ", eventId=" + eventId +
                ", studentId=" + studentId +
                ", status='" + status + '\'' +
                '}';
    }
}
