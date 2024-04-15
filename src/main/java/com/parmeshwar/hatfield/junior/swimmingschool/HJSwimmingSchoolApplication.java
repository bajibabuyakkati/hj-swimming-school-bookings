package com.parmeshwar.hatfield.junior.swimmingschool;

import java.util.Scanner;

/**
 * Application to the managing the bookings made by learners from Hatfield Junior Swimming School
 */
public class HJSwimmingSchoolApplication {
    /**
     * Scanner to take user input values from the command prompt.
     */
    private static final Scanner scanner = new Scanner(System.in);

    //Starts the application from main method.
    public static void main(String[] args) {
        System.out.flush();
        System.out.println("=======================================================================");
        System.out.println("Welcome to the Hatfield Junior Swimming School (HJSS) Management System");
        System.out.println("=======================================================================");
        while (true) {
            System.out.println("Please choose an number from below options");
            System.out.println("===========================================");
            System.out.println("1. Add Learner");
            System.out.println("2. Remove Learner");
            System.out.println("3. Add Coach");
            System.out.println("4. Remove Coach");
            System.out.println("5. Add Session");
            System.out.println("6. Remove Session");
            System.out.println("7. View Timetable by Day");
            System.out.println("8. View Timetable by Grade");
            System.out.println("9. View Timetable by Coach");
            System.out.println("10. Book Session");
            System.out.println("11. Cancel Session");
            System.out.println("12. Attend Session");
            System.out.println("13. Write Review");
            System.out.println("14. Generate Learners Report");
            System.out.println("15. Generate Coaches Report");
            System.out.println("0. Exit");
            System.out.println("===========================================");
            System.out.print("Enter your option number: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            _onNavigatetoUserChoice(choice);
        }
    }

    private static void _onNavigatetoUserChoice(int choice) {
        switch (choice) {
            case 1:
                _displayHeading("You have selected the Add Learner option.");
                AppUtil.addLearner();
                break;
            case 2:
                _displayHeading("You have selected the Remove Learner option.");
                AppUtil.removeLearner();
                break;
            case 3:
                _displayHeading("Add Coach");
                AppUtil.addCoach();
                break;
            case 4:
                _displayHeading("Remove Coach");
                AppUtil.removeCoach();
                break;
            case 5:
                _displayHeading("Add Session");
                AppUtil.addSession();
                break;
            case 6:
                _displayHeading("Remove Session");
                AppUtil.removeSession();
                break;
            case 7:
                _displayHeading("Timetable by Day");
                AppUtil.timeTableByDayView();
                break;
            case 8:
                _displayHeading("Timetable by Grade");
                AppUtil.timeTableByGradeView();
                break;
            case 9:
                _displayHeading("Timetable by Coach");
                AppUtil.timeTableByCoachView();
                break;
            case 10:
                _displayHeading("Book Session");
                AppUtil.bookSession();
                break;
            case 11:
                _displayHeading("Cancel Session");
                AppUtil.cancelSession();
                break;
            case 12:
                _displayHeading("Attend Session");
                AppUtil.attendSession();
                break;
            case 13:
                _displayHeading("Write Review");
                AppUtil.writeReview();
                break;
            case 14:
                _displayHeading("Learners Report");
                AppUtil.getLearnersReport();
                break;
            case 15:
                _displayHeading("Coach Report");
                AppUtil.getCoachsReport();
                break;
            case 0:
                _displayHeading("Exiting...");
                return;
            default:
                _displayHeading("Invalid choice! Please try again.");
                scanner.nextLine();

        }
    }

    private static void _displayHeading(String message) {
        System.out.println("===========================================");
        System.out.println(message);
        System.out.println("===========================================");
    }
}
