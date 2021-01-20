package com.college.applicant.classifier.CollegeApplicantClassifier;

import com.college.applicant.classifier.CollegeApplicantClassifier.data.ApplicantRepository;
import com.college.applicant.classifier.CollegeApplicantClassifier.data.impl.ApplicantRepositoryImpl;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;
import com.college.applicant.classifier.CollegeApplicantClassifier.rules.RulesCriteria;
import com.college.applicant.classifier.CollegeApplicantClassifier.rules.impl.RulesCriteriaImpl;
import com.college.applicant.classifier.CollegeApplicantClassifier.service.ApplicantService;
import com.college.applicant.classifier.CollegeApplicantClassifier.service.impl.ApplicantServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class CollegeApplicantClassifierIntegrationTest {

    private ApplicantService applicantService;
    private ApplicantRepository applicantRepository;
    private RulesCriteria rulesCriteria;
    Map<String, String> instantRejectApplicantMap;
    Map<String, String> instantAcceptApplicantMap;
    Map<String, String> furtherReviewApplicantMap;

    @Before
    public void setUp() {
        applicantRepository = new ApplicantRepositoryImpl();
        rulesCriteria = new RulesCriteriaImpl();
        applicantService = new ApplicantServiceImpl(rulesCriteria, applicantRepository);

        instantRejectApplicantMap = new HashMap<>();
        instantRejectApplicantMap.put("firstName", "Tri");
        instantRejectApplicantMap.put("lastName", "NguyeN");
        instantRejectApplicantMap.put("felonies", "1");
        instantRejectApplicantMap.put("recentYearOfFelony", "2018");
        instantRejectApplicantMap.put("actScores", "30");
        instantRejectApplicantMap.put("satScores", "1452");
        instantRejectApplicantMap.put("gpaScale", "6.0");
        instantRejectApplicantMap.put("gpa", "2.1");
        instantRejectApplicantMap.put("state", "CA");
        instantRejectApplicantMap.put("age", "12");

        instantAcceptApplicantMap = new HashMap<>();
        instantAcceptApplicantMap.put("firstName", "Elisa");
        instantAcceptApplicantMap.put("lastName", "Nguyen");
        instantAcceptApplicantMap.put("felonies", "0");
        instantAcceptApplicantMap.put("recentYearOfFelony", "");
        instantAcceptApplicantMap.put("actScores", "");
        instantAcceptApplicantMap.put("satScores", "2000");
        instantAcceptApplicantMap.put("gpaScale", "6.0");
        instantAcceptApplicantMap.put("gpa", "5.9");
        instantAcceptApplicantMap.put("state", "TX");
        instantAcceptApplicantMap.put("age", "19");

        furtherReviewApplicantMap = new HashMap<>();
        furtherReviewApplicantMap.put("firstName", "Tom");
        furtherReviewApplicantMap.put("lastName", "Levine");
        furtherReviewApplicantMap.put("felonies", "0");
        furtherReviewApplicantMap.put("recentYearOfFelony", "");
        furtherReviewApplicantMap.put("actScores", "");
        furtherReviewApplicantMap.put("satScores", "2000");
        furtherReviewApplicantMap.put("gpaScale", "4.0");
        furtherReviewApplicantMap.put("gpa", "3.0");
        furtherReviewApplicantMap.put("state", "TX");
        furtherReviewApplicantMap.put("age", "19");
    }

    @Test
    public void testCollegeApplicantClassifier() {
        String rejectReason = "Last Name is not in correct form. Has more than one felony over the past 5 years. GPA is below 70% criteria. ";
        Applicant instantRejectApplicant = applicantService.convertToApplicantObj(instantRejectApplicantMap);
        Applicant instantAcceptApplicant = applicantService.convertToApplicantObj(instantAcceptApplicantMap);
        Applicant furtherReviewApplicant = applicantService.convertToApplicantObj(furtherReviewApplicantMap);

        applicantService.saveToDatabase(instantRejectApplicant);
        applicantService.saveToDatabase(instantAcceptApplicant);
        applicantService.saveToDatabase(furtherReviewApplicant);

        Result instantRejectApplicantResult = applicantService.getApplicantClassifier(instantRejectApplicant.getId());
        Result instantAcceptApplicantResult = applicantService.getApplicantClassifier(instantAcceptApplicant.getId());
        Result furtherReviewApplicantResult = applicantService.getApplicantClassifier(furtherReviewApplicant.getId());

        Assert.assertEquals(instantAcceptApplicantResult, Result.INSTANT_ACCEPT);
        Assert.assertEquals(furtherReviewApplicantResult, Result.FURTHER_REVIEW);
        Assert.assertEquals(instantRejectApplicantResult, Result.INSTANT_REJECT);
        Assert.assertEquals(instantRejectApplicantResult.getReason(), rejectReason);
    }
}
