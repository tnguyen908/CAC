package com.college.applicant.classifier.CollegeApplicantClassifier.rules;

import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;
import com.college.applicant.classifier.CollegeApplicantClassifier.rules.impl.RulesCriteriaImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RulesCriteriaUnitTest {
    private RulesCriteriaImpl rulesCriteria;

    @Before
    public void setup() {
        rulesCriteria = new RulesCriteriaImpl();
    }

    @Test
    public void testNameReject_NoReject() {
        String correctForm = "Tri";
        Assert.assertFalse(rulesCriteria.nameReject(correctForm));
    }

    @Test
    public void testNameReject_Reject() {
        String lowerCaseFirstLetter = "tri";
        String capitalFirstLetterUpperCaseOtherLetters = "TrI";

        Assert.assertTrue(rulesCriteria.nameReject(lowerCaseFirstLetter));
        Assert.assertTrue(rulesCriteria.nameReject(capitalFirstLetterUpperCaseOtherLetters));
    }

    @Test
    public void testAgeMinimumReject_NoReject() {
        int age1 = 20;
        int age2 = 100;

        Assert.assertFalse(rulesCriteria.ageMinimumReject(age1));
        Assert.assertFalse(rulesCriteria.ageMinimumReject(age2));
    }

    @Test
    public void testAgeMinimumReject_Reject() {
        int age1 = -20;
        int age2 = -1;

        Assert.assertTrue(rulesCriteria.ageMinimumReject(age1));
        Assert.assertTrue(rulesCriteria.ageMinimumReject(age2));
    }

    @Test
    public void testFelonyReject_NoReject() {
        int felonyCount = 0;

        Assert.assertFalse(rulesCriteria.felonyReject(felonyCount, 0));
    }

    @Test
    public void testFelonyReject_Reject() {
        int felonyCountOne = 1;
        int felonyCountThree = 3;

        Assert.assertTrue(rulesCriteria.felonyReject(felonyCountOne, 2016));
        Assert.assertTrue(rulesCriteria.felonyReject(felonyCountThree, 2020));
    }

    @Test
    public void testGPAReject_NoReject() {
        double gpaOne = 3.8;
        double gpaOneRange = 4.0;
        double gpaTwo = 3.5;
        double gpaTwoRange = 5.0;

        Assert.assertFalse(rulesCriteria.gpaReject(gpaOne, gpaOneRange));
        Assert.assertFalse(rulesCriteria.gpaReject(gpaTwo, gpaTwoRange));
    }

    @Test
    public void testGPAReject_Reject() {
        double gpaOne = 3.45;
        double gpaOneRange = 5.0;
        double gpaTwo = 2.3;
        double gpaTwoRange = 4.0;

        Assert.assertTrue(rulesCriteria.gpaReject(gpaOne, gpaOneRange));
        Assert.assertTrue(rulesCriteria.gpaReject(gpaTwo, gpaTwoRange));
    }

    @Test
    public void testAgeAcceptance_Accept() {
        int ageAboveEighty = 81;
        int ageAboveEightyOne = 90;
        int ageInRange = 17;
        int ageInRange2 = 25;
        int randomAge = 21;

        Assert.assertTrue(rulesCriteria.ageAcceptance(ageAboveEighty));
        Assert.assertTrue(rulesCriteria.ageAcceptance(ageAboveEightyOne));
        Assert.assertTrue(rulesCriteria.ageAcceptance(ageInRange));
        Assert.assertTrue(rulesCriteria.ageAcceptance(ageInRange2));
        Assert.assertTrue(rulesCriteria.ageAcceptance(randomAge));
    }

    @Test
    public void testAgeAcceptance_NoAcceptance() {
        int ageAtEighty = 80;
        int ageBelowRange = 15;
        int ageAboveRange = 32;
        int ageAtMaxRange = 26;

        Assert.assertFalse(rulesCriteria.ageAcceptance(ageAtEighty));
        Assert.assertFalse(rulesCriteria.ageAcceptance(ageBelowRange));
        Assert.assertFalse(rulesCriteria.ageAcceptance(ageAboveRange));
        Assert.assertFalse(rulesCriteria.ageAcceptance(ageAtMaxRange));
    }

    @Test
    public void testGPAAccept_Accept() {
        double gpaOne = 5.0;
        double gpaOneRange = 5.0;
        double gpaTwo = 3.6;
        double gpaTwoRange = 4.0;

        Assert.assertTrue(rulesCriteria.gpaAccept(gpaOne, gpaOneRange));
        Assert.assertTrue(rulesCriteria.gpaAccept(gpaTwo, gpaTwoRange));
    }

    @Test
    public void testGPAAccept_NoAccept() {
        double gpaOne = 3.5;
        double gpaOneRange = 4.0;
        double gpaTwo = 3.0;
        double gpaTwoRange = 5.0;

        Assert.assertFalse(rulesCriteria.gpaAccept(gpaOne, gpaOneRange));
        Assert.assertFalse(rulesCriteria.gpaAccept(gpaTwo, gpaTwoRange));
    }

    @Test
    public void testActSatScoreAccept_Accept() {
        int actScore = 28;
        int satScore = 1921;

        Assert.assertTrue(rulesCriteria.actSatScoreAccept(actScore, satScore));
    }

    @Test
    public void testActSatScoreAccept_NoAccept() {
        int actScore = 27;
        int satScore = 1920;
        int actScore1 = 20;
        int satScore1 = 1750;

        Assert.assertFalse(rulesCriteria.actSatScoreAccept(actScore, satScore));
        Assert.assertFalse(rulesCriteria.actSatScoreAccept(actScore1, satScore1));
    }

    @Test
    public void testHasNotBeenRejected_HasBeenRejected() {
        Applicant applicant = new Applicant();
        applicant.setResult(Result.INSTANT_REJECT);

        Assert.assertFalse(rulesCriteria.hasNotBeenRejected(applicant));
    }

    @Test
    public void testHasNotBeenRejected_HasNotBeenRejected() {
        Applicant applicant = new Applicant();
        applicant.setResult(Result.FURTHER_REVIEW);

        Applicant applicant1 = new Applicant();
        applicant1.setResult(Result.FURTHER_REVIEW);

        Assert.assertTrue(rulesCriteria.hasNotBeenRejected(applicant));
        Assert.assertTrue(rulesCriteria.hasNotBeenRejected(applicant1));
    }

    @Test
    public void testCheckAcceptance() {
        Applicant applicant = new Applicant();
        applicant.setAge(17);
        applicant.setGpa(3.8);
        applicant.setGpaScale(4.0);
        applicant.setActScores(28);
        applicant.setSatScores(1921);
        applicant.setResult(Result.FURTHER_REVIEW);

        rulesCriteria.checkAcceptance(applicant);
        Assert.assertEquals(applicant.getResult(), Result.INSTANT_ACCEPT);
    }

    @Test
    public void testCheckReject() {
        String rejectReasons = "First Name is not in correct form. Last Name is not in correct form. Negative Age. Has more than one felony over the past 5 years. GPA is below 70% criteria. ";
        Applicant applicant = new Applicant();
        applicant.setFelonies(1);
        applicant.setYearOfRecentFelony(2017);
        applicant.setGpa(2.0);
        applicant.setGpaScale(4.0);
        applicant.setAge(-1);
        applicant.setFirstName("tri");
        applicant.setLastName("NguyEn");

        rulesCriteria.checkReject(applicant);

        Assert.assertEquals(applicant.getResult(), Result.INSTANT_REJECT);
        Assert.assertEquals(applicant.getResult().getReason(), rejectReasons);

    }

    @Test
    public void testSetRejectReason() {
        StringBuilder rejectReason = new StringBuilder();
        String reason = "Test string for instant reject reason. ";
        Applicant applicant = new Applicant();

        rulesCriteria.setRejectReason(rejectReason, reason, applicant);

        Assert.assertEquals(applicant.getResult(), Result.INSTANT_REJECT);
        Assert.assertEquals(applicant.getResult().getReason().trim(), reason.trim());
    }

    @Test
    public void testGetApplicantResult_InstantAccept() {
        Applicant applicant = new Applicant();
        applicant.setId("TestID");
        applicant.setFirstName("Tri");
        applicant.setLastName("Nguyen");
        applicant.setState("CA");
        applicant.setAge(81);
        applicant.setGpa(3.8);
        applicant.setGpaScale(4.0);
        applicant.setSatScores(1921);
        applicant.setActScores(-1);
        applicant.setFelonies(1);
        applicant.setYearOfRecentFelony(2015);
        applicant.setResult(Result.FURTHER_REVIEW);
        applicant.getResult().setReason("N/A");

        rulesCriteria.getApplicantResult(applicant);

        Assert.assertEquals(applicant.getResult(), Result.INSTANT_ACCEPT);
    }

    @Test
    public void testGetApplicantResult_InstantReject() {
        String rejectReason = "Has more than one felony over the past 5 years. GPA is below 70% criteria. ";
        Applicant applicant = new Applicant();
        applicant.setId("TestID");
        applicant.setFirstName("Tri");
        applicant.setLastName("Nguyen");
        applicant.setState("CA");
        applicant.setAge(67);
        applicant.setGpa(2.75);
        applicant.setGpaScale(4.0);
        applicant.setSatScores(1921);
        applicant.setActScores(29);
        applicant.setFelonies(2);
        applicant.setYearOfRecentFelony(2018);
        applicant.setResult(Result.FURTHER_REVIEW);
        applicant.getResult().setReason("N/A");

        rulesCriteria.getApplicantResult(applicant);

        Assert.assertEquals(applicant.getResult(), Result.INSTANT_REJECT);
        Assert.assertEquals(applicant.getResult().getReason(), rejectReason);
    }

    @Test
    public void testGetApplicantResult_FurtherReview() {
        Applicant applicant = new Applicant();
        applicant.setId("TestID");
        applicant.setFirstName("Tri");
        applicant.setLastName("Nguyen");
        applicant.setState("CA");
        applicant.setAge(67);
        applicant.setGpa(3.0);
        applicant.setGpaScale(4.0);
        applicant.setSatScores(1800);
        applicant.setActScores(25);
        applicant.setFelonies(0);
        applicant.setYearOfRecentFelony(0);
        applicant.setResult(Result.FURTHER_REVIEW);
        applicant.getResult().setReason("N/A");

        rulesCriteria.getApplicantResult(applicant);

        Assert.assertEquals(applicant.getResult(), Result.FURTHER_REVIEW);
    }
}
