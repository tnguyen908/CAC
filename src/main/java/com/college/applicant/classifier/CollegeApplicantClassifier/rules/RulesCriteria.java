package com.college.applicant.classifier.CollegeApplicantClassifier.rules;

import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;

public interface RulesCriteria {
    Integer felonyReject = 1;
    Integer felonyYearRange = 5;
    Integer gpaRejectPercentage = 70;
    Integer ageMinimumReject = 0;
    Integer startAgeRangeAccept = 17;
    Integer endAgeRangeAccept = 26;
    Integer aboveAgeEightyAcceptance = 80;
    Integer gpaAcceptancePercentage = 90;
    Integer satScoreAcceptance = 1920;
    Integer actScoreAcceptance = 27;

    Result getApplicantResult(Applicant applicant);
}
