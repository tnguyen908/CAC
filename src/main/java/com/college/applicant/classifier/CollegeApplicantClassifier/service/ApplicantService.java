package com.college.applicant.classifier.CollegeApplicantClassifier.service;

import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;

import java.util.Map;

public interface ApplicantService {
    Applicant convertToApplicantObj(Map<String, String> userData);
    void saveToDatabase(Applicant applicant);
    Result getApplicantClassifier(String id);
}
