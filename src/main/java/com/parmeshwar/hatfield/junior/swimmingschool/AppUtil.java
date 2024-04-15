package com.parmeshwar.hatfield.junior.swimmingschool;

import com.parmeshwar.hatfield.junior.swimmingschool.exceptions.CoachExistsException;
import com.parmeshwar.hatfield.junior.swimmingschool.exceptions.LearnerAgeLimitException;
import com.parmeshwar.hatfield.junior.swimmingschool.exceptions.LearnerExistsException;
import com.hatfield.junior.swimmingschool.modal.*;
import com.parmeshwar.hatfield.junior.swimmingschool.service.HJSwimmingSchool;
import com.parmeshwar.hatfield.junior.swimmingschool.service.HJSwimmingSchoolImpl;
import com.parmeshwar.hatfield.junior.swimmingschool.modal.*;

import java.util.Scanner;

public class AppUtil {
    /**
     * Scanner to take user input values from the command prompt.
     */
    public static final Scanner scanner = new Scanner(System.in);

    public static final HJSwimmingSchool hjSwimmingSchool = new HJSwimmingSchoolImpl();

    public static void addLearner() {
        System.out.print("Enter Learner Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Learner Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Learner Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Learner Emergency Contact: ");
        String emergencyContact = scanner.nextLine();
        System.out.print("Enter Learner Grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        try {
            hjSwimmingSchool.addLearner(
                    new Learner(
                            name,
                            gender,
                            age,
                            emergencyContact,
                            grade
                    )
            );
            System.out.println("Learner " +name+ " Added Successfully!");
            scanner.nextLine();
        } catch (LearnerExistsException | LearnerAgeLimitException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }


    public static void removeLearner() {
        System.out.print("Enter learner name: ");
        Learner learner = hjSwimmingSchool.getLearners().stream().filter(
                l -> l.getName().equals(scanner.nextLine())
        ).findFirst().orElse(null);
        if (learner == null) {
            System.out.println("Learner not found!");
            scanner.nextLine();
            return;
        }
        hjSwimmingSchool.removeLearner(learner);
        System.out.println("Learner "+learner.getName()+" removed successfully!");
        scanner.nextLine();
    }

    public static void addCoach() {
        System.out.print("Enter coach name: ");
        String coachName = scanner.nextLine();
        try {
            hjSwimmingSchool.addCoach(
                    new Coach(
                            coachName
                    )
            );
            System.out.println("Coach added successfully!");
            scanner.nextLine();
        } catch (CoachExistsException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    public static void removeCoach() {
        System.out.print("Enter coach name: ");
        Coach coach = hjSwimmingSchool.getCoaches().stream().filter(
                c -> c.getName().equals(scanner.nextLine())
        ).findFirst().orElse(null);
        if (coach == null) {
            System.out.println("Coach not found!");
            scanner.nextLine();
            return;
        }
        hjSwimmingSchool.removeCoach(coach);
        System.out.println("Coach removed successfully!");
        scanner.nextLine();
    }

    public static void addSession() {
        System.out.print("Days");
        System.out.println("1. "+ SessionDay.MONDAY);
        System.out.println("2. "+ SessionDay.WEDNESDAY);
        System.out.println("3. "+ SessionDay.FRIDAY);
        System.out.println("4. "+ SessionDay.SATURDAY);
        System.out.println("0. Exit");
        SessionDay day = null;
        System.out.print("Enter day: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                day = SessionDay.MONDAY;
                break;
            case 2:
                day = SessionDay.WEDNESDAY;
                break;
            case 3:
                day = SessionDay.FRIDAY;
                break;
            case 4:
                day = SessionDay.SATURDAY;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        System.out.print("Times");
        System.out.println("1. "+ BookTimeSlot.From2To3);
        System.out.println("2. "+ BookTimeSlot.From3To4);
        System.out.println("3. "+ BookTimeSlot.From4To5);
        System.out.println("4. "+ BookTimeSlot.From5To6);
        System.out.println("5. "+ BookTimeSlot.From6To7);
        System.out.println("0. Exit");
        BookTimeSlot time = null;
        System.out.print("Enter time: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                time = BookTimeSlot.From2To3;
                break;
            case 2:
                time = BookTimeSlot.From3To4;
                break;
            case 3:
                time = BookTimeSlot.From4To5;
                break;
            case 4:
                time = BookTimeSlot.From5To6;
                break;
            case 5:
                time = BookTimeSlot.From6To7;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter coach name: ");
        String coachName = scanner.nextLine();
        Coach coach = hjSwimmingSchool.getCoaches().stream().filter(
                c -> c.getName().equals(coachName)
        ).findFirst().orElse(null);
        if (coach == null) {
            System.out.println("Coach not found!");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();
        hjSwimmingSchool.addSession(
                new SwimmingSession(
                        (int) Math.random()*1000,
                        time,
                        coach,
                        day,
                        grade
                )
        );
        System.out.println("Session added successfully!");
        scanner.nextLine();
    }

    public static void removeSession() {
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = hjSwimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        hjSwimmingSchool.removeSession(
                session
        );
        System.out.println("Session removed successfully!");
        scanner.nextLine();
    }

    public static void timeTableByDayView() {
        System.out.print("Days");
        System.out.println("1. "+ SessionDay.MONDAY);
        System.out.println("2. "+ SessionDay.WEDNESDAY);
        System.out.println("3. "+ SessionDay.FRIDAY);
        System.out.println("4. "+ SessionDay.SATURDAY);
        System.out.println("0. Exit");
        SessionDay day = null;
        System.out.print("Enter day: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                day = SessionDay.MONDAY;
                break;
            case 2:
                day = SessionDay.WEDNESDAY;
                break;
            case 3:
                day = SessionDay.FRIDAY;
                break;
            case 4:
                day = SessionDay.SATURDAY;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice");
                scanner.nextLine();
                return;
        }
        hjSwimmingSchool.timeTableByDayView(day);
        scanner.nextLine();
    }

    public static void timeTableByGradeView() {
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        hjSwimmingSchool.timeTableByGradeView(grade);
        scanner.nextLine();
    }

    public static void timeTableByCoachView() {
        System.out.print("Enter coach name: ");
        String coachName = scanner.nextLine();
        Coach coach = hjSwimmingSchool.getCoaches().stream().filter(
                c -> c.getName().equals(coachName)
        ).findFirst().orElse(null);

        if (coach == null) {
            System.out.println("Coach not found!");
            scanner.nextLine();
            return;
        }

        hjSwimmingSchool.timeTableByCoachView(
                coach
        );
        scanner.nextLine();
    }

    public static void bookSession() {
        System.out.print("Enter learner name: ");
        String learnerName = scanner.nextLine();
        Learner learner = hjSwimmingSchool.getLearners().stream().filter(
                l -> l.getName().equalsIgnoreCase(learnerName)
        ).findFirst().orElse(null);
        if (learner == null) {
            System.out.println("Learner not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = hjSwimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        try {
            hjSwimmingSchool.bookSession(
                    learner,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Session booked successfully!");
        scanner.nextLine();
    }

    public static void cancelSession() {
        System.out.print("Enter learner name: ");
        String learnerName = scanner.nextLine();
        Learner learner = hjSwimmingSchool.getLearners().stream().filter(
                l -> l.getName().equals(learnerName)
        ).findFirst().orElse(null);
        if (learner == null) {
            System.out.println("Learner not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = hjSwimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        try {
            hjSwimmingSchool.cancelSession(
                    learner,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Session cancelled successfully!");
        scanner.nextLine();
    }

    public static void attendSession() {
        System.out.print("Enter learner name: ");
        String learnerName = scanner.nextLine();
        Learner learner = hjSwimmingSchool.getLearners().stream().filter(
                l -> l.getName().equals(learnerName)
        ).findFirst().orElse(null);
        if (learner == null) {
            System.out.println("Learner not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = hjSwimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            return;
        }
        try {
            hjSwimmingSchool.attendSession(
                    learner,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Session attended successfully!");
        scanner.nextLine();
    }

    public static void writeReview() {
        System.out.print("Enter learner name: ");
        String learnerName = scanner.nextLine();
        Learner learner = hjSwimmingSchool.getLearners().stream().filter(
                l -> l.getName().equalsIgnoreCase(learnerName)
        ).findFirst().orElse(null);
        if (learner == null) {
            System.out.println("Learner not found!");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter Session ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = hjSwimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter review: 1-5");
        int review = scanner.nextInt();
        scanner.nextLine();
        try {
            hjSwimmingSchool.writeReview(
                    learner,
                    session,
                    review
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Review written successfully!");
        scanner.nextLine();
    }

    public static void getLearnersReport(){
        hjSwimmingSchool.generateLearnersReport();
    }

    public static void getCoachsReport(){
        hjSwimmingSchool.generateCoachsReport();
    }

    public static String getRatingDescription(int rating) {
        return switch (rating) {
            case 1 -> "Very dissatisfied";
            case 2 -> "Dissatisfied";
            case 3 -> "Ok";
            case 4 -> "Satisfied";
            case 5 -> "Very Satisfied";
            default -> "Not rated";
        };
    }
}
