package com.parmeshwar.hatfield.junior.swimmingschool.reports;

import com.parmeshwar.hatfield.junior.swimmingschool.modal.Learner;
import com.parmeshwar.hatfield.junior.swimmingschool.service.HJSwimmingSchool;

import java.util.List;

public class LearnersReport implements Report {

    @Override
    public void getReport(HJSwimmingSchool hjSwimmingSchool) {
        List<Learner> learners = hjSwimmingSchool.getLearners();
        System.out.println("============================================");
        System.out.println("Learners Report");
        System.out.println("============================================");
        for (Learner learner : learners) {
            System.out.println(learner);
        }
        System.out.println("============================================");
    }
}
