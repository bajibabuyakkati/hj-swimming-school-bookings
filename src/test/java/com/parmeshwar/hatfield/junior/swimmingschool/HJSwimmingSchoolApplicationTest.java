package com.parmeshwar.hatfield.junior.swimmingschool;

import com.parmeshwar.hatfield.junior.swimmingschool.exceptions.CoachExistsException;
import com.parmeshwar.hatfield.junior.swimmingschool.exceptions.LearnerAgeLimitException;
import com.parmeshwar.hatfield.junior.swimmingschool.modal.*;
import com.parmeshwar.hatfield.junior.swimmingschool.service.HJSwimmingSchool;
import com.parmeshwar.hatfield.junior.swimmingschool.service.HJSwimmingSchoolImpl;

public class HJSwimmingSchoolApplicationTest {

    public static void main(String[] args) throws LearnerAgeLimitException {
        HJSwimmingSchoolImpl swimmingSchool = new HJSwimmingSchoolImpl();

        System.out.println("Adding 5 coach list...");
        Coach coach1 = new Coach("Coach1");
        Coach coach2 = new Coach("Coach2");
        Coach coach3 = new Coach("Coach3");
        Coach coach4 = new Coach("Coach4");
        Coach coach5 = new Coach("Coach5");
        try {
            swimmingSchool.addCoach(coach1);
            swimmingSchool.addCoach(coach2);
            swimmingSchool.addCoach(coach3);
            swimmingSchool.addCoach(coach4);
            swimmingSchool.addCoach(coach5);
        } catch (CoachExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Adding 10 learner list...");
        Learner learner1 = new Learner("Learner1", "Female", 5, "123456789", 1);
        Learner learner2 = new Learner("Learner2", "Male", 7, "123456789", 1);
        Learner learner3 = new Learner("Learner3", "Female", 8, "123456789", 1);
        Learner learner4 = new Learner("Learner4", "Male", 7, "123456789", 1);
        Learner learner5 = new Learner("Learner5", "Female", 9, "123456789", 1);
        Learner learner6 = new Learner("Learner6", "Female", 6, "123456789", 1);
        Learner learner7 = new Learner("Learner7", "Male", 7, "123456789", 1);
        Learner learner8 = new Learner("Learner8", "Female", 8, "123456789", 1);
        Learner learner9 = new Learner("Learner9", "Female", 10, "123456789", 1);
        Learner learner10 = new Learner("Learner10", "Male", 6, "123456789", 1);

        try {
            swimmingSchool.addLearner(learner1);
            swimmingSchool.addLearner(learner2);
            swimmingSchool.addLearner(learner3);
            swimmingSchool.addLearner(learner4);
            swimmingSchool.addLearner(learner5);
            swimmingSchool.addLearner(learner6);
            swimmingSchool.addLearner(learner7);
            swimmingSchool.addLearner(learner8);
            swimmingSchool.addLearner(learner9);
            swimmingSchool.addLearner(learner10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Adding 5 lesson list...");
        SwimmingSession lesson1 = new SwimmingSession(1, BookTimeSlot.From2To3, coach1, SessionDay.MONDAY, 2);
        SwimmingSession lesson2 = new SwimmingSession(2, BookTimeSlot.From3To4, coach2, SessionDay.WEDNESDAY, 1);
        SwimmingSession lesson3 = new SwimmingSession(3, BookTimeSlot.From4To5, coach3, SessionDay.FRIDAY, 2);
        SwimmingSession lesson4 = new SwimmingSession(4, BookTimeSlot.From5To6, coach4, SessionDay.SATURDAY, 1);
        SwimmingSession lesson5 = new SwimmingSession(5, BookTimeSlot.From6To7, coach5, SessionDay.MONDAY, 2);
        try {
            swimmingSchool.addSession(lesson1);
            swimmingSchool.addSession(lesson2);
            swimmingSchool.addSession(lesson3);
            swimmingSchool.addSession(lesson4);
            swimmingSchool.addSession(lesson5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Booking the lessons...");
        bookLesson(swimmingSchool, learner1, lesson1);
        bookLesson(swimmingSchool, learner1, lesson2);
        bookLesson(swimmingSchool, learner2, lesson2);
        bookLesson(swimmingSchool, learner2, lesson3);
        bookLesson(swimmingSchool, learner3, lesson3);
        bookLesson(swimmingSchool, learner3, lesson4);
        bookLesson(swimmingSchool, learner4, lesson4);
        bookLesson(swimmingSchool, learner4, lesson5);
        bookLesson(swimmingSchool, learner5, lesson5);
        bookLesson(swimmingSchool, learner5, lesson1);

        System.out.println("Attending the lessons...");
        attendLesson(swimmingSchool, learner1, lesson1);
        attendLesson(swimmingSchool, learner1, lesson2);
        attendLesson(swimmingSchool, learner2, lesson2);
        attendLesson(swimmingSchool, learner2, lesson3);
        attendLesson(swimmingSchool, learner4, lesson5);
        attendLesson(swimmingSchool, learner5, lesson5);
        attendLesson(swimmingSchool, learner5, lesson1);

        System.out.println("Cancelling the lessons...");
        cancelLesson(swimmingSchool, learner3, lesson3);
        cancelLesson(swimmingSchool, learner3, lesson4);
        cancelLesson(swimmingSchool, learner4, lesson4);

        // Display timetables
        System.out.println("Displaying timetables for Monday");
        swimmingSchool.timeTableByDayView(SessionDay.MONDAY);
        System.out.println("Displaying timetables for Grade 2");
        swimmingSchool.timeTableByGradeView(2);
        System.out.println("Displaying timetables for Coach1");
        swimmingSchool.timeTableByCoachView(coach1);

        // Adding reviews
        System.out.println("Adding reviews...");
        try{
            swimmingSchool.writeReview(learner1, lesson1,5);
            swimmingSchool.writeReview(learner1, lesson2,4);
            swimmingSchool.writeReview(learner2, lesson2,4);
            swimmingSchool.writeReview(learner2, lesson3,4);
            swimmingSchool.writeReview(learner4, lesson5,3);
            swimmingSchool.writeReview(learner5, lesson5,2);
            swimmingSchool.writeReview(learner5, lesson1,5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Generate reports
        System.out.println("Learners Report View..");
        swimmingSchool.generateLearnersReport();

        System.out.println("Coaches Report View...");
        swimmingSchool.generateCoachsReport();
    }

    private static void bookLesson(HJSwimmingSchool swimmingSchool, Learner learner, SwimmingSession lesson) {
        try {
            swimmingSchool.bookSession(learner, lesson);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void attendLesson(HJSwimmingSchool swimmingSchool, Learner learner, SwimmingSession lesson) {
        try {
            swimmingSchool.attendSession(learner, lesson);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cancelLesson(HJSwimmingSchool swimmingSchool, Learner learner, SwimmingSession lesson) {
        try {
            swimmingSchool.cancelSession(learner, lesson);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
