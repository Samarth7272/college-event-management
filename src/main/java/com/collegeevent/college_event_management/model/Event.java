package com.collegeevent.college_event_management.model;

import java.sql.Date;
import java.sql.Time;

public class Event {

    private int eventId;
    private String title;
    private String description;
    private Integer organizerId;
    private Integer venueId;
    private Date eventDate;
    private Time startTime;
    private Time endTime;
    private int seatsAvailable;

    public Event() {}

    // -------- GETTERS --------
    
    public int getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    // -------- SETTERS --------
    
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setSeatsAvailable(int seatsAvailable) {   // <-- Missing setter added
        this.seatsAvailable = seatsAvailable;
    }

    @Override
    public String toString() {
        return eventId + ": " + title + " | Date: " + eventDate + " | Seats: " + seatsAvailable;
    }
}
