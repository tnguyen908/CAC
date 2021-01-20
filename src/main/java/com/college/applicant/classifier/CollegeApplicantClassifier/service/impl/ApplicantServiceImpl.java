package com.college.applicant.classifier.CollegeApplicantClassifier.service.impl;

import com.college.applicant.classifier.CollegeApplicantClassifier.data.ApplicantRepository;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;
import com.college.applicant.classifier.CollegeApplicantClassifier.rules.RulesCriteria;
import com.college.applicant.classifier.CollegeApplicantClassifier.service.ApplicantService;

import java.util.Map;
import java.util.UUID;

public class ApplicantServiceImpl implements ApplicantService {

    ApplicantRepository applicantRepository;
    RulesCriteria rulesCriteria;

    public ApplicantServiceImpl(RulesCriteria rulesCriteria, ApplicantRepository applicantRepository) {
        this.rulesCriteria = rulesCriteria;
        this.applicantRepository = applicantRepository;
    }

    @Override
    public Applicant convertToApplicantObj(Map<String, String> userData) {
        Applicant newApplicant = new Applicant();

        newApplicant.setId(generateUniqueId());
        newApplicant.setFirstName(userData.get("firstName"));
        newApplicant.setLastName(userData.get("lastName"));
        newApplicant.setState(userData.get("state"));
        newApplicant.setAge(Integer.parseInt(userData.get("age")));
        newApplicant.setGpa(Double.parseDouble(userData.get("gpa")));
        newApplicant.setGpaScale(Double.parseDouble(userData.get("gpaScale")));

        //If No SAT scores then -1 will be input as for SAT scores
        if(userData.get("satScores").equals("")) {
            newApplicant.setSatScores(-1);
        } else {
            newApplicant.setSatScores(Integer.parseInt(userData.get("satScores")));
        }

        //If No ACT scores then -1 will be input as for ACT scores
        if(userData.get("actScores").equals("")) {
            newApplicant.setActScores(-1);
        } else {
            newApplicant.setActScores(Integer.parseInt(userData.get("actScores")));
        }

        newApplicant.setFelonies(Integer.parseInt(userData.get("felonies")));

        if(userData.get("recentYearOfFelony").equals("")) {
            newApplicant.setYearOfRecentFelony(0);
        } else {
            newApplicant.setYearOfRecentFelony(Integer.parseInt(userData.get("recentYearOfFelony")));
        }

        newApplicant.setResult(Result.FURTHER_REVIEW);
        newApplicant.getResult().setReason("N/A");

        return newApplicant;
    }

    @Override
    public void saveToDatabase(Applicant applicant) {
        applicantRepository.saveApplicant(applicant.getId(), applicant);
    }

    @Override
    public Result getApplicantClassifier(String id) {
        Applicant applicant = applicantRepository.getApplicant(id);
        return rulesCriteria.getApplicantResult(applicant);
    }

    private static String generateUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
