package com.college.applicant.classifier.CollegeApplicantClassifier.rules.impl;

import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;
import com.college.applicant.classifier.CollegeApplicantClassifier.rules.RulesCriteria;

import java.util.Calendar;

//Class that list requirements for criteria and does checking for acceptance or rejection.
public class RulesCriteriaImpl implements RulesCriteria {
    private static final String firstNameRejectReason = "First Name is not in correct form.";
    private static final String lastNameRejectReason = "Last Name is not in correct form.";
    private static final String ageRejectReason = "Negative Age.";
    private static final String felonyRejectReason = "Has more than one felony over the past 5 years.";
    private static final String gpaRejectReason = "GPA is below 70% criteria.";

    public boolean nameReject(String name) {
        if(!Character.isUpperCase(name.charAt(0))) {
            return true;
        }

        char[] charArray = name.toCharArray();

        for(int i=1; i < charArray.length; i++){
            if(!Character.isLowerCase(charArray[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean ageMinimumReject(int age) {
        return age < ageMinimumReject;
    }

    public boolean felonyReject(int felony, int yearOfRecentFelony) {
        if(felony == 0) {
            return false;
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return felony >= felonyReject && (currentYear-yearOfRecentFelony) <= felonyYearRange;
    }

    public boolean gpaReject(double gpa, double gpaRange) {
        return (gpa/gpaRange)*100 < gpaRejectPercentage;
    }

    public boolean ageAcceptance(int age) {
        return age >= startAgeRangeAccept && age < endAgeRangeAccept || age > aboveAgeEightyAcceptance;
    }

    public boolean gpaAccept(double gpa, double gpaRange) {
        return (gpa/gpaRange)*100 >= gpaAcceptancePercentage;
    }

    public boolean actSatScoreAccept(int actScore, int satScore) {
        return actScore > actScoreAcceptance || satScore > satScoreAcceptance;
    }

    public boolean hasNotBeenRejected(Applicant applicant) {
        return applicant.getResult() != Result.INSTANT_REJECT;
    }

    public void checkAcceptance(Applicant applicant) {
        if(ageAcceptance(applicant.getAge()) && gpaAccept(applicant.getGpa(), applicant.getGpaScale()) && actSatScoreAccept(applicant.getActScores(), applicant.getSatScores()) && hasNotBeenRejected(applicant)) {
            applicant.setResult(Result.INSTANT_ACCEPT);
        }
    }

    public void checkReject(Applicant applicant) {
        StringBuilder rejectReason = new StringBuilder();

        if(nameReject(applicant.getFirstName())) {
            setRejectReason(rejectReason, firstNameRejectReason, applicant);
        }

        if(nameReject(applicant.getLastName())) {
            setRejectReason(rejectReason, lastNameRejectReason, applicant);
        }

        if(ageMinimumReject(applicant.getAge())) {
            setRejectReason(rejectReason, ageRejectReason, applicant);
        }

        if(felonyReject(applicant.getFelonies(), applicant.getYearOfRecentFelony())) {
            setRejectReason(rejectReason, felonyRejectReason, applicant);
        }

        if(gpaReject(applicant.getGpa(), applicant.getGpaScale())) {
            setRejectReason(rejectReason, gpaRejectReason, applicant);
        }
    }

    public void setRejectReason(StringBuilder rejectReason, String reason, Applicant applicant) {
        applicant.setResult(Result.INSTANT_REJECT);
        rejectReason.append(reason);
        rejectReason.append(" ");
        applicant.getResult().setReason(rejectReason.toString());
    }

    @Override
    public Result getApplicantResult(Applicant applicant) {
        //Order matters here. Should run Rejection criteria first.
        checkReject(applicant);

        if(applicant.getResult() == Result.INSTANT_REJECT) {
            return applicant.getResult();
        }

        checkAcceptance(applicant);
        return applicant.getResult();
    }
}
