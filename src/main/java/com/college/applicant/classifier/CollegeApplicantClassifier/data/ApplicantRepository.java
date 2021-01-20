package com.college.applicant.classifier.CollegeApplicantClassifier.data;

import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;

import java.util.List;

//Class to simulate a database. Should instead cover Applicant class to a Applicant DTO if connecting to real DB.
public interface ApplicantRepository {
    void saveApplicant(String id, Applicant applicant);
    Applicant getApplicant(String id);
    void updateApplicant(String id, Applicant applicant);
    boolean deleteApplicant(String id);
    List<Applicant> getAllApplicant();
}
