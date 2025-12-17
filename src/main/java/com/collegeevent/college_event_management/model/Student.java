package com.collegeevent.college_event_management.model;

public class Student {

    private int studentId;
    private int userId;
    private String name;
    private String email;
    private String department;
    private int year;

    public Student() {}

    public Student(int studentId, int userId, String name, String email, String department, int year) {
        this.studentId = studentId;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.year = year;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {   // <<< THIS METHOD WAS MISSING
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", year=" + year +
                '}';
    }
}
