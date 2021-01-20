package com.college.applicant.classifier.CollegeApplicantClassifier.service;

import com.college.applicant.classifier.CollegeApplicantClassifier.data.ApplicantRepository;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;
import com.college.applicant.classifier.CollegeApplicantClassifier.rules.RulesCriteria;
import com.college.applicant.classifier.CollegeApplicantClassifier.service.impl.ApplicantServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class ApplicantServiceUnitTest {

    private ApplicantService applicantService;

    @Mock
    private ApplicantRepository applicantRepository;
    @Mock
    private RulesCriteria rulesCriteria;

    private Map<String, String> applicantData;
    private Applicant applicant;

    @Before
    public void setup() {
        applicantService = new ApplicantServiceImpl(rulesCriteria, applicantRepository);
        applicantData = new HashMap<>();

        applicantData.put("firstName", "Tri");
        applicantData.put("lastName", "Nguyen");
        applicantData.put("felonies", "0");
        applicantData.put("recentYearOfFelony", "");
        applicantData.put("actScores", "29");
        applicantData.put("satScores", "1921");
        applicantData.put("gpaScale", "4.0");
        applicantData.put("gpa", "3.8");
        applicantData.put("state", "CA");
        applicantData.put("age", "23");

        applicant = new Applicant();
        applicant.setId("TestID");
        applicant.setFirstName("Tri");
        applicant.setLastName("Nguyen");
        applicant.setState("CA");
        applicant.setAge(23);
        applicant.setGpa(3.8);
        applicant.setGpaScale(4.0);
        applicant.setSatScores(1921);
        applicant.setActScores(29);
        applicant.setFelonies(0);
        applicant.setResult(Result.FURTHER_REVIEW);
        applicant.getResult().setReason("N/A");
    }

    @Test
    public void testConvertToApplicantObj() {
        Applicant newApplicant = applicantService.convertToApplicantObj(applicantData);
        Assert.assertEquals(newApplicant.getFirstName(), applicant.getFirstName());
        Assert.assertEquals(newApplicant.getLastName(), applicant.getLastName());
        Assert.assertEquals(newApplicant.getState(), applicant.getState());
        Assert.assertEquals(newApplicant.getAge(), applicant.getAge());
        Assert.assertEquals(newApplicant.getGpa(), applicant.getGpa());
        Assert.assertEquals(newApplicant.getGpaScale(), applicant.getGpaScale());
        Assert.assertEquals(newApplicant.getSatScores(), applicant.getSatScores());
        Assert.assertEquals(newApplicant.getActScores(), applicant.getActScores());
        Assert.assertEquals(newApplicant.getFelonies(), applicant.getFelonies());
    }
}
