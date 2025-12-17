package com.collegeevent.college_event_management.model;

public class UserAccount {

    private int userId;
    private String username;
    private String passwordHash;
    private String role; // "student", "organizer", "admin"

    public UserAccount() {}

    public UserAccount(int userId, String username, String passwordHash, String role) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // ---- GETTERS ----
    
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    // ---- SETTERS ----

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {   // <-- The missing method
        this.passwordHash = passwordHash;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // ---- Debugging output ----
    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
