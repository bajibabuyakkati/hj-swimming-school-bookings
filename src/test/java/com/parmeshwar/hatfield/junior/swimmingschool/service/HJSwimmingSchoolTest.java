package com.parmeshwar.hatfield.junior.swimmingschool.service;

import com.parmeshwar.hatfield.junior.swimmingschool.exceptions.*;
import com.parmeshwar.hatfield.junior.swimmingschool.modal.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class HJSwimmingSchoolTest {

    private HJSwimmingSchool hjSwimmingSchool;
    private Learner learner;
    private SwimmingSession session;
    @Before
    public void setUp() throws LearnerAgeLimitException {
        hjSwimmingSchool = new HJSwimmingSchoolImpl();
        learner = new Learner("LearnerOne","Male", 7,"+12345678912",1);
        session = new SwimmingSession(1234,2);
    }


    @Test
    public void testAddLearnerSuccessful() throws LearnerExistsException, LearnerAgeLimitException {
        hjSwimmingSchool.addLearner(learner);
        assertTrue(hjSwimmingSchool.getLearners().contains(learner));
    }

    @Test(expected = LearnerExistsException.class)
    public void testAddLearnerAlreadyExists() throws LearnerExistsException, LearnerAgeLimitException {
        hjSwimmingSchool.addLearner(learner); // Adding the learner once
        hjSwimmingSchool.addLearner(learner); // Trying to add the same learner again should throw an exception
    }

    @Test
    public void testRemoveLearnerSuccessful() throws LearnerExistsException, LearnerAgeLimitException {
        hjSwimmingSchool.addLearner(learner);
        assertTrue(hjSwimmingSchool.getLearners().contains(learner));
        hjSwimmingSchool.removeLearner(learner);
        assertFalse(hjSwimmingSchool.getLearners().contains(learner));
    }

    @Test
    public void testAddCoachSuccessful() throws CoachExistsException {
        Coach coach = new Coach("CoachOne");
        hjSwimmingSchool.addCoach(coach);
        assertTrue(hjSwimmingSchool.getCoaches().contains(coach));
    }

    @Test(expected = CoachExistsException.class)
    public void testAddCoachAlreadyExists() throws CoachExistsException {
        Coach coach = new Coach("CoachThree");
        hjSwimmingSchool.addCoach(coach); // Adding the coach once
        hjSwimmingSchool.addCoach(coach); // Trying to add the same coach again should throw an exception
    }

    @Test
    public void testRemoveCoachSuccessful() throws CoachExistsException {
        Coach coach = new Coach("CoachOne");
        hjSwimmingSchool.addCoach(coach);
        assertTrue(hjSwimmingSchool.getCoaches().contains(coach));
        hjSwimmingSchool.removeCoach(coach);
        assertFalse(hjSwimmingSchool.getCoaches().contains(coach));
    }

    @Test
    public void testAssignCoachToSessionSuccessful() throws SwimmingSessionAlreadyHasCoachException {
        Coach coach = new Coach("CoachOne");
        SwimmingSession session = new SwimmingSession(1);
        hjSwimmingSchool.assignCoachToSession(coach, session);
        assertEquals(coach, session.getCoach());
    }

    @Test(expected = SwimmingSessionAlreadyHasCoachException.class)
    public void testAssignCoachToSessionAlreadyHasCoach() throws SwimmingSessionAlreadyHasCoachException {
        Coach coach1 = new Coach("CoachOne");
        Coach coach2 = new Coach("CoachTwo");
        SwimmingSession session = new SwimmingSession(2);
        hjSwimmingSchool.assignCoachToSession(coach1, session); // Assigning the first coach
        hjSwimmingSchool.assignCoachToSession(coach2, session); // Trying to assign another coach should throw an exception
    }

    @Test
    public void testBookSessionSuccessful() throws SessionNotEligibleException, SessionAlreadyBookedException, SessionFullBookedException, LearnerExistsException {
        hjSwimmingSchool.addLearner(learner);
        hjSwimmingSchool.addSession(session);
        hjSwimmingSchool.bookSession(learner, session);
        assertTrue(learner.isSessionBooked(session));
        assertTrue(session.getAttendees().contains(learner));
    }

    @Test(expected = SessionAlreadyBookedException.class)
    public void testBookSessionAlreadyBooked() throws SessionNotEligibleException, SessionAlreadyBookedException, SessionFullBookedException, LearnerExistsException {
        hjSwimmingSchool.addLearner(learner);
        hjSwimmingSchool.addSession(session);
        hjSwimmingSchool.bookSession(learner, session);
        hjSwimmingSchool.bookSession(learner, session);
    }

    @Test
    public void testCancelSessionSuccessful() throws SessionNotEligibleException, SessionAlreadyBookedException, SessionFullBookedException, SessionNotBookedException, SessionAttendedException, LearnerExistsException {
        hjSwimmingSchool.addLearner(learner);
        hjSwimmingSchool.addSession(session);
        hjSwimmingSchool.bookSession(learner, session);
        hjSwimmingSchool.cancelSession(learner, session);
        assertFalse(learner.isSessionBooked(session));
        assertFalse(session.getAttendees().contains(learner));
    }

    @Test(expected = SessionNotBookedException.class)
    public void testCancelSessionNotBooked() throws SessionNotBookedException, SessionAttendedException {
        hjSwimmingSchool.cancelSession(learner, session);
    }

    @Test
    public void testAttendSessionSuccessful() throws SessionNotEligibleException, SessionAlreadyBookedException, SessionFullBookedException, SessionNotBookedException, LearnerExistsException {
        hjSwimmingSchool.addLearner(learner);
        hjSwimmingSchool.addSession(session);
        hjSwimmingSchool.bookSession(learner, session);
        hjSwimmingSchool.attendSession(learner, session);
        assertTrue(learner.isSessionAttended(session));
    }

    @Test(expected = SessionNotBookedException.class)
    public void testAttendSessionNotBooked() throws SessionNotBookedException {
        hjSwimmingSchool.attendSession(learner, session);
    }

    @Test
    public void testWriteReviewSuccessful() throws SessionNotEligibleException, InvalidRatingException, SessionWriteReviewException, LearnerExistsException, SessionFullBookedException, SessionAlreadyBookedException, SessionNotBookedException {
        hjSwimmingSchool.addLearner(learner);
        hjSwimmingSchool.addSession(session);
        hjSwimmingSchool.bookSession(learner, session);
        hjSwimmingSchool.attendSession(learner, session);

        int rating = 5;
        hjSwimmingSchool.writeReview(learner, session, rating);

        Review review = hjSwimmingSchool.getReviews().get(0);
        assertEquals(learner, review.getLearner());
        assertEquals(session, review.getSession());
        assertEquals(rating, review.getRating());
    }

    @Test(expected = SessionNotEligibleException.class)
    public void testWriteReviewNotEligibleForSession() throws SessionNotEligibleException, InvalidRatingException, SessionWriteReviewException, LearnerExistsException, SessionFullBookedException, SessionAlreadyBookedException {
        SwimmingSession session = new SwimmingSession(1234, 3);
        hjSwimmingSchool.addLearner(learner);
        hjSwimmingSchool.addSession(session);
        hjSwimmingSchool.bookSession(learner, session);
        hjSwimmingSchool.writeReview(learner, session, 5); // Learner hasn't booked or attended the session
    }

    @Test(expected = SessionWriteReviewException.class)
    public void testWriteReviewNotAttendedSession() throws InvalidRatingException, SessionWriteReviewException, LearnerExistsException, SessionFullBookedException, SessionAlreadyBookedException {
        hjSwimmingSchool.addLearner(learner);
        hjSwimmingSchool.addSession(session);
        hjSwimmingSchool.writeReview(learner, session, 5); // Learner hasn't attended the session
    }

    @Test(expected = InvalidRatingException.class)
    public void testWriteReviewRatingOutOfRange() throws SessionNotEligibleException, InvalidRatingException, SessionWriteReviewException, LearnerExistsException, SessionFullBookedException, SessionAlreadyBookedException, SessionNotBookedException {
        hjSwimmingSchool.addLearner(learner);
        hjSwimmingSchool.addSession(session);
        hjSwimmingSchool.bookSession(learner, session);
        hjSwimmingSchool.attendSession(learner, session);
        hjSwimmingSchool.writeReview(learner, session, 6); // Rating out of range
    }

    @Test
    public void testTimeTableByDayView() {
        Coach coach = new Coach("UserOne");
        SwimmingSession session = new SwimmingSession(1, BookTimeSlot.From2To3, coach, SessionDay.MONDAY, 1);
        hjSwimmingSchool.addSession(session);
        String output = getOutput(() -> hjSwimmingSchool.timeTableByDayView(SessionDay.MONDAY));
        assertNotNull(output);
        assertNotEquals(-1, output.indexOf("Swimming Sessions on Monday:"));
        assertNotEquals(-1, output.indexOf("2:00 PM - 3:00 PM"));
    }

    @Test
    public void testTimeTableByGradeView() {
        Coach coach = new Coach("UserOne");
        SwimmingSession session = new SwimmingSession(1, BookTimeSlot.From2To3, coach, SessionDay.MONDAY, 1);
        hjSwimmingSchool.addSession(session);

        // Redirect System.out to a string for comparison
        System.out.println("============================================");
        String expectedOutput = "============================================\n" +
                "Swimming Sessions for Grade 1:\n" +
                "============================================\n" +
                String.format("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Coach") +
                String.format("%-20s%-20s%-20s%-20s%-20s\n", session.getId(), "Monday", "2:00 PM - 3:00 PM", "1", "UserOne") +
                String.format("%-20s%-20s%-20s%-20s%-20s\n", session.getId(), "Monday", "2:00 PM - 3:00 PM", "1", "UserOne") +
                "============================================\n";
        String output = getOutput(() -> hjSwimmingSchool.timeTableByGradeView(1));
        assertNotNull(output);
        assertNotEquals(-1, output.indexOf("Swimming Sessions for Grade 1:"));
        assertNotEquals(-1, output.indexOf("2:00 PM - 3:00 PM"));
    }

    @Test
    public void testTimeTableByCoachView() {
        Coach coach = new Coach("UserOne");
        SwimmingSession session = new SwimmingSession(1, BookTimeSlot.From2To3, coach, SessionDay.MONDAY, 1);
        hjSwimmingSchool.addSession(session);
        String output = getOutput(() -> hjSwimmingSchool.timeTableByCoachView(coach));
        assertNotNull(getOutput(() -> hjSwimmingSchool.timeTableByCoachView(coach)));
        assertNotEquals(-1, output.indexOf("Swimming Sessions by UserOne:"));
        assertNotEquals(-1, output.indexOf("2:00 PM - 3:00 PM"));
    }

    // Helper method to capture System.out
    private String getOutput(Runnable code) {
        var outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        code.run();
        System.setOut(System.out);
        return outContent.toString();
    }
}