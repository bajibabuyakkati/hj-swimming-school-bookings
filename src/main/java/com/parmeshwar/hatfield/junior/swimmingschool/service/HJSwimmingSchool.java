package com.parmeshwar.hatfield.junior.swimmingschool.service;

import com.hatfield.junior.swimmingschool.exceptions.*;
import com.hatfield.junior.swimmingschool.modal.*;
import com.parmeshwar.hatfield.junior.swimmingschool.exceptions.*;
import com.parmeshwar.hatfield.junior.swimmingschool.modal.*;

import java.util.List;

public interface HJSwimmingSchool {

    void timeTableByDayView(SessionDay day);
    void timeTableByGradeView(int grade);
    void timeTableByCoachView(Coach coach);

    void bookSession(Learner learner, SwimmingSession session) throws SessionNotEligibleException, SessionAlreadyBookedException, SessionFullBookedException;
    void cancelSession(Learner learner, SwimmingSession session) throws SessionNotBookedException, SessionAttendedException;
    void attendSession(Learner learner, SwimmingSession session) throws SessionNotBookedException;

    void assignCoachToSession(Coach coach, SwimmingSession session) throws SwimmingSessionAlreadyHasCoachException;

    void addLearner(Learner learner) throws LearnerExistsException;
    void removeLearner(Learner learner);

    void addCoach(Coach coach) throws CoachExistsException;
    void removeCoach(Coach coach);

    void addSession(SwimmingSession session);
    void removeSession(SwimmingSession session);

    void writeReview(Learner learner, SwimmingSession session, int rating) throws InvalidRatingException, SessionWriteReviewException;

    void generateLearnersReport();
    void generateCoachsReport();

    List<SwimmingSession> getSessions();

    List<Coach> getCoaches();

    List<Learner> getLearners();

    List<Review> getReviews();
}
