package com.collegeevent.college_event_management;

import com.collegeevent.college_event_management.dao.EventDAO;
import com.collegeevent.college_event_management.dao.RegistrationDAO;
import com.collegeevent.college_event_management.dao.StudentDAO;
import com.collegeevent.college_event_management.dao.UserAccountDAO;
import com.collegeevent.college_event_management.model.Event;
import com.collegeevent.college_event_management.model.Registration;
import com.collegeevent.college_event_management.model.Student;
import com.collegeevent.college_event_management.model.UserAccount;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserAccountDAO userDao = new UserAccountDAO();
    private static final StudentDAO studentDao = new StudentDAO();
    private static final EventDAO eventDao = new EventDAO();
    private static final RegistrationDAO registrationDao = new RegistrationDAO();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    registerStudentFlow();
                    break;

                case 2:
                    loginFlow();
                    break;

                case 3:
                    listEvents();
                    break;

                case 4:
                    System.out.println("Exiting... Bye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n===== College Event Management =====");
        System.out.println("1. Register as Student");
        System.out.println("2. Login");
        System.out.println("3. View Events (no login)");
        System.out.println("4. Exit");
    }

    // ---------- Helper input methods ----------
    private static int readInt(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private static String readLine(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    // ---------- Flows ----------

    private static void registerStudentFlow() {
        System.out.println("\n--- Student Registration ---");

        String username = readLine("Choose a username: ");
        String password = readLine("Choose a password: ");

        String name = readLine("Full Name: ");
        String email = readLine("Email: ");
        String dept = readLine("Department: ");
        int year = readInt("Year (1-4): ");

        int userId = userDao.createStudentUser(username, password);
        if (userId <= 0) {
            System.out.println("Could not create user. Maybe username already exists.");
            return;
        }

        Student s = new Student();
        s.setUserId(userId);
        s.setName(name);
        s.setEmail(email);
        s.setDepartment(dept);
        s.setYear(year);

        int studentId = studentDao.createStudent(s);
        if (studentId <= 0) {
            System.out.println("Could not create student profile.");
            return;
        }

        System.out.println("✅ Student registered successfully! Your student ID: " + studentId);
    }

    private static void loginFlow() {
        System.out.println("\n--- Login ---");

        String username = readLine("Username: ");
        String password = readLine("Password: ");

        UserAccount user = userDao.findByUsername(username);
        if (user == null || !user.getPasswordHash().equals(password)) {
            System.out.println("❌ Invalid username or password.");
            return;
        }

        System.out.println("✅ Login successful. Role: " + user.getRole());

        String role = user.getRole();
        if ("student".equals(role)) {
            Student s = studentDao.findByUserId(user.getUserId());
            if (s == null) {
                System.out.println("No student profile found for this user.");
                return;
            }
            studentMenu(s);
        } else if ("organizer".equals(role)) {
            System.out.println("Organizer menu not implemented yet.");
        } else if ("admin".equals(role)) {
            System.out.println("Admin menu not implemented yet.");
        } else {
            System.out.println("Unknown role.");
        }
    }

    private static void listEvents() {
        System.out.println("\n--- Events ---");
        List<Event> events = eventDao.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }
        for (Event e : events) {
            System.out.println(e.getEventId() + ". " + e.getTitle() +
                    " | Date: " + e.getEventDate() +
                    " | Seats: " + e.getSeatsAvailable());
        }
    }

    // ---------- Student menu ----------

    private static void studentMenu(Student s) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Menu (" + s.getName() + ") ---");
            System.out.println("1. View Events");
            System.out.println("2. Register for Event");
            System.out.println("3. View My Registrations");
            System.out.println("4. Logout");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    listEvents();
                    break;

                case 2:
                    registerForEventFlow(s);
                    break;

                case 3:
                    listMyRegistrations(s);
                    break;

                case 4:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private static void registerForEventFlow(Student s) {
        listEvents();
        int eventId = readInt("Enter event ID to register: ");
        boolean ok = registrationDao.registerStudentForEvent(s.getStudentId(), eventId);
        if (ok) {
            System.out.println("✅ Registered for event " + eventId);
        } else {
            System.out.println("❌ Could not register (maybe already registered or invalid event).");
        }
    }

    private static void listMyRegistrations(Student s) {
        System.out.println("\n--- My Registrations ---");
        List<Registration> regs = registrationDao.getRegistrationsByStudent(s.getStudentId());
        if (regs.isEmpty()) {
            System.out.println("You have no registrations.");
            return;
        }
        for (Registration r : regs) {
            System.out.println("Registration ID: " + r.getRegistrationId() +
                    " | Event ID: " + r.getEventId() +
                    " | Status: " + r.getStatus());
        }
    }
}
