package com.parmeshwar.hatfield.junior.swimmingschool.modal;

public class Review {
    private Learner learner;
    private int rating;
    private SwimmingSession session;
    public Review(Learner learner, int rating, SwimmingSession session) {
        this.learner = learner;
        this.rating = rating;
        this.session = session;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public SwimmingSession getSession() {
        return session;
    }

    public void setSession(SwimmingSession session) {
        this.session = session;
    }
}
