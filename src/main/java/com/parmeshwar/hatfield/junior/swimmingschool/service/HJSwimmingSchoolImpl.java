package com.parmeshwar.hatfield.junior.swimmingschool.service;

import com.parmeshwar.hatfield.junior.swimmingschool.exceptions.*;
import com.parmeshwar.hatfield.junior.swimmingschool.modal.*;
import com.parmeshwar.hatfield.junior.swimmingschool.reports.CoachesReport;
import com.parmeshwar.hatfield.junior.swimmingschool.reports.LearnersReport;
import com.parmeshwar.hatfield.junior.swimmingschool.reports.Report;

import java.util.ArrayList;
import java.util.List;

public class HJSwimmingSchoolImpl implements HJSwimmingSchool{


    private List<SwimmingSession> sessions;
    private List<Coach> coaches;
    private List<Learner> learners;
    private List<Review> reviews;
    private Report report;

    public HJSwimmingSchoolImpl(){
        this.sessions = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.learners = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    @Override
    public void assignCoachToSession(Coach coach, SwimmingSession session) throws SwimmingSessionAlreadyHasCoachException {
        if(session.getCoach() != null){
            throw new SwimmingSessionAlreadyHasCoachException("This session already has a coach");
        }
        session.setCoach(coach);
    }

    @Override
    public void addLearner(Learner learner) throws LearnerExistsException {
        if(learners.contains(learner)){
            throw new LearnerExistsException("Learner already exists");
        }
        learners.add(learner);
    }

    @Override
    public void removeLearner(Learner learner) {
        learners.remove(learner);
    }

    @Override
    public void addCoach(Coach coach) throws CoachExistsException {
        if(coaches.contains(coach)){
            throw new CoachExistsException("Coach already exists");
        }
        coaches.add(coach);
    }

    @Override
    public void removeCoach(Coach coach) {
        coaches.remove(coach);
    }

    @Override
    public void addSession(SwimmingSession session) {
        if(!sessions.contains(session)){
            sessions.add(session);
        }
        sessions.add(session);
    }

    @Override
    public void removeSession(SwimmingSession session) {
        sessions.remove(session);
    }

    @Override
    public void timeTableByDayView(SessionDay day) {
        List<SwimmingSession> sessionsByDay = sessions.stream()
                .filter(session -> session.getDay() == day).toList();
        printSeparator();
        System.out.println("Swimming Sessions on " + day + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Coach");
        for(SwimmingSession session : sessionsByDay){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getTimeSlot(),
                    session.getGrade(),
                    session.getCoach().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void timeTableByGradeView(int grade) {
        List<SwimmingSession> sessionsByGrade = sessions.stream()
                .filter(session -> session.getGrade() == grade)
                .toList();
        printSeparator();
        System.out.println("Swimming Sessions for Grade " + grade + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Coach");
        for(SwimmingSession session : sessionsByGrade){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getTimeSlot(),
                    session.getGrade(),
                    session.getCoach().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void timeTableByCoachView(Coach coach) {
        List<SwimmingSession> sessionsByCoach = sessions.stream()
                .filter(session -> session.getCoach().equals(coach))
                .toList();
        printSeparator();
        System.out.println("Swimming Sessions by " + coach.getName() + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Coach");
        for(SwimmingSession session : sessionsByCoach){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getTimeSlot(),
                    session.getGrade(),
                    session.getCoach().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void bookSession(Learner learner, SwimmingSession session) throws SessionNotEligibleException, SessionAlreadyBookedException, SessionFullBookedException {
        if(!learner.isEligibleForSession(session)){
            throw new SessionNotEligibleException("You are not eligible for this session");
        }
        if(learner.isSessionBooked(session)){
            throw new SessionAlreadyBookedException("You are already attending this session");
        }
        if(session.isFull()){
            throw new SessionFullBookedException("This session is full");
        }
        session.addAttendee(learner);
        learner.bookSession(session);
    }

    @Override
    public void cancelSession(Learner learner, SwimmingSession session) throws SessionNotBookedException, SessionAttendedException {
        if(!learner.isSessionBooked(session)){
            throw new SessionNotBookedException("You are not attending this session");
        }
        if(learner.isSessionAttended(session)){
            throw new SessionAttendedException("You cannot cancel a session you have already attended");
        }
        session.removeAttendee(learner);
        learner.cancelSession(session);
    }

    @Override
    public void attendSession(Learner learner, SwimmingSession session) throws SessionNotBookedException {
        if(!learner.isSessionBooked(session)){
            throw new SessionNotBookedException("You can't attend a session you haven't booked");
        }
        learner.attendSession(session);
    }

    @Override
    public void writeReview(Learner learner, SwimmingSession session, int rating) throws InvalidRatingException, SessionWriteReviewException {
        if(rating < 1 || rating > 5){
            throw new InvalidRatingException("Rating must be between 1 and 5");
        }
        System.out.println("session.getAttendees().contains(learner)");
        System.out.println(session.getAttendees().contains(learner));
        if(!session.getAttendees().contains(learner)){
            throw new SessionWriteReviewException("You can't write a review for a session you haven't attended");
        }
        reviews.add(
                new Review(learner, rating, session)
        );
    }

    @Override
    public void generateLearnersReport() {
        report = new LearnersReport();
        report.getReport(this);
    }

    @Override
    public void generateCoachsReport() {
        report = new CoachesReport();
        report.getReport(this);
    }

    public List<SwimmingSession> getSessions() {
        return sessions;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<Learner> getLearners() {
        return learners;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    private void printSeparator(){
        System.out.println("============================================");
    }
}
