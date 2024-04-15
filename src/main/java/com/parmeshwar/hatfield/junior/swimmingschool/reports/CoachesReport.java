package com.parmeshwar.hatfield.junior.swimmingschool.reports;

import com.parmeshwar.hatfield.junior.swimmingschool.AppUtil;
import com.parmeshwar.hatfield.junior.swimmingschool.modal.Coach;
import com.parmeshwar.hatfield.junior.swimmingschool.modal.Review;
import com.parmeshwar.hatfield.junior.swimmingschool.service.HJSwimmingSchool;

import java.util.List;

public class CoachesReport implements Report {

    @Override
    public void getReport(HJSwimmingSchool hjSwimmingSchool) {
        List<Review> reviews = hjSwimmingSchool.getReviews();
        List<Coach> coaches = hjSwimmingSchool.getCoaches();
        System.out.println("============================================");
        System.out.println("Coaches Report");
        System.out.println("============================================");
        System.out.println("Number of coaches: " + coaches.size());
        System.out.println("Coaches: ");
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Average Rating","Global Rating");
        for (Coach coach : coaches) {
            double rating = getAverageRating(reviews, coach);
            System.out.printf("%-20s%-20s%-20s\n", coach.getName(), rating, AppUtil.getRatingDescription((int) rating));
        }
        System.out.println("============================================");
    }

    private double getAverageRating(List<Review> reviews, Coach coach) {
        double sum = 0;
        int count = 0;
        for (Review review : reviews) {
            if (review.getSession().getCoach().equals(coach)) {
                sum += review.getRating();
                count++;
            }
        }
        return sum / count;
    }
}
