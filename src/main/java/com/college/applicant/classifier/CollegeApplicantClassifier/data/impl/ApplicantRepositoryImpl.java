package com.college.applicant.classifier.CollegeApplicantClassifier.data.impl;

import com.college.applicant.classifier.CollegeApplicantClassifier.data.ApplicantRepository;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;

import java.util.*;

//Class to simulate a database. Should instead cover Applicant class to a Applicant DTO if connecting to real DB.
public class ApplicantRepositoryImpl implements ApplicantRepository {

    Map<String, Applicant> applicantStore = new HashMap<>();

    @Override
    public void saveApplicant(String id, Applicant applicant) {
        if(!applicantStore.containsKey(id)) {
            applicantStore.put(id, applicant);
        }
    }

    @Override
    public Applicant getApplicant(String id) {
        Applicant applicant = applicantStore.get(id);
        if(applicant == null) {
            return new Applicant();
        }
        return applicant;
    }

    @Override
    public void updateApplicant(String id, Applicant applicant) {
        if(applicantStore.containsKey(id)) {
            applicantStore.put(id, applicant);
        }
    }

    @Override
    public boolean deleteApplicant(String id) {
        if(applicantStore.containsKey(id)) {
            applicantStore.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Applicant> getAllApplicant() {
        return new ArrayList<>(applicantStore.values());
    }
}
