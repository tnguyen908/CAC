package com.college.applicant.classifier.CollegeApplicantClassifier.data;

import com.college.applicant.classifier.CollegeApplicantClassifier.data.impl.ApplicantRepositoryImpl;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ApplicantRepositoryUnitTest {

    private ApplicantRepository applicantRepository;
    private Applicant applicant;

    @Before
    public void setup() {
        applicantRepository = new ApplicantRepositoryImpl();

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
    public void testSaveApplicant_NewApplicant() {
        //Save Applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);

        //Retrieve Applicant
        Applicant retrievedApplicant = applicantRepository.getApplicant("TestID");

        //Compare to make sure they are the same
        Assert.assertEquals(retrievedApplicant.getId(), applicant.getId());
        Assert.assertEquals(retrievedApplicant.getFirstName(), applicant.getFirstName());
        Assert.assertEquals(retrievedApplicant.getLastName(), applicant.getLastName());
        Assert.assertEquals(retrievedApplicant.getState(), applicant.getState());
        Assert.assertEquals(retrievedApplicant.getAge(), applicant.getAge());
        Assert.assertEquals(retrievedApplicant.getGpa(), applicant.getGpa());
        Assert.assertEquals(retrievedApplicant.getGpaScale(), applicant.getGpaScale());
        Assert.assertEquals(retrievedApplicant.getSatScores(), applicant.getSatScores());
        Assert.assertEquals(retrievedApplicant.getActScores(), applicant.getActScores());
        Assert.assertEquals(retrievedApplicant.getFelonies(), applicant.getFelonies());
        Assert.assertEquals(retrievedApplicant.getResult(), applicant.getResult());
        Assert.assertEquals(retrievedApplicant.getResult().getReason(), applicant.getResult().getReason());
    }

    @Test
    public void testSaveApplicant_AlreadyExistApplicant() {

        //Save applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);

        //Save again to see if applicantRepository will save new applicant with same Id
        Applicant newApplicantSameID = new Applicant();
        newApplicantSameID.setId("TestID");
        newApplicantSameID.setFirstName("Tom");
        newApplicantSameID.setLastName("Levine");
        newApplicantSameID.setState("CA");
        newApplicantSameID.setAge(23);
        newApplicantSameID.setGpa(3.8);
        newApplicantSameID.setGpaScale(4.0);
        newApplicantSameID.setSatScores(1921);
        newApplicantSameID.setActScores(29);
        newApplicantSameID.setFelonies(0);
        newApplicantSameID.setResult(Result.FURTHER_REVIEW);
        newApplicantSameID.getResult().setReason("N/A");

        //Save new applicant with same Id
        applicantRepository.saveApplicant(applicant.getId(), newApplicantSameID);

        //Retrieve applicant and check for difference in first and last name
        Applicant retrievedApplicant = applicantRepository.getApplicant("TestID");

        Assert.assertEquals(retrievedApplicant.getId(), newApplicantSameID.getId());
        Assert.assertNotEquals(retrievedApplicant.getFirstName(), newApplicantSameID.getFirstName());
        Assert.assertNotEquals(retrievedApplicant.getLastName(), newApplicantSameID.getLastName());
        Assert.assertEquals(retrievedApplicant.getState(), newApplicantSameID.getState());
        Assert.assertEquals(retrievedApplicant.getAge(), newApplicantSameID.getAge());
        Assert.assertEquals(retrievedApplicant.getGpa(), newApplicantSameID.getGpa());
        Assert.assertEquals(retrievedApplicant.getGpaScale(), newApplicantSameID.getGpaScale());
        Assert.assertEquals(retrievedApplicant.getSatScores(), newApplicantSameID.getSatScores());
        Assert.assertEquals(retrievedApplicant.getActScores(), newApplicantSameID.getActScores());
        Assert.assertEquals(retrievedApplicant.getFelonies(), newApplicantSameID.getFelonies());
        Assert.assertEquals(retrievedApplicant.getResult(), newApplicantSameID.getResult());
        Assert.assertEquals(retrievedApplicant.getResult().getReason(), newApplicantSameID.getResult().getReason());
    }

    @Test
    public void testGetApplicant_ValidId() {

        //Save Applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);

        //Get Applicant with Valid Id
        Applicant retrievedApplicant = applicantRepository.getApplicant(applicant.getId());

        //Compare to make sure they are the same
        Assert.assertEquals(retrievedApplicant.getId(), applicant.getId());
        Assert.assertEquals(retrievedApplicant.getFirstName(), applicant.getFirstName());
        Assert.assertEquals(retrievedApplicant.getLastName(), applicant.getLastName());
        Assert.assertEquals(retrievedApplicant.getState(), applicant.getState());
        Assert.assertEquals(retrievedApplicant.getAge(), applicant.getAge());
        Assert.assertEquals(retrievedApplicant.getGpa(), applicant.getGpa());
        Assert.assertEquals(retrievedApplicant.getGpaScale(), applicant.getGpaScale());
        Assert.assertEquals(retrievedApplicant.getSatScores(), applicant.getSatScores());
        Assert.assertEquals(retrievedApplicant.getActScores(), applicant.getActScores());
        Assert.assertEquals(retrievedApplicant.getFelonies(), applicant.getFelonies());
        Assert.assertEquals(retrievedApplicant.getResult(), applicant.getResult());
        Assert.assertEquals(retrievedApplicant.getResult().getReason(), applicant.getResult().getReason());
    }

    @Test
    public void testGetApplicant_InvalidId() {
        //Save Applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);

        //Get Applicant with Valid Id
        Applicant retrievedApplicant = applicantRepository.getApplicant("Test2");

        //Compare to make sure they are the same
        Assert.assertEquals(retrievedApplicant.getId(), null);
        Assert.assertEquals(retrievedApplicant.getFirstName(), null);
        Assert.assertEquals(retrievedApplicant.getLastName(), null);
        Assert.assertEquals(retrievedApplicant.getState(), null);
        Assert.assertEquals(retrievedApplicant.getAge(), null);
        Assert.assertEquals(retrievedApplicant.getGpa(), null);
        Assert.assertEquals(retrievedApplicant.getGpaScale(), null);
        Assert.assertEquals(retrievedApplicant.getSatScores(), null);
        Assert.assertEquals(retrievedApplicant.getActScores(), null);
        Assert.assertEquals(retrievedApplicant.getFelonies(), null);
        Assert.assertEquals(retrievedApplicant.getResult(), null);
    }

    @Test
    public void testUpdateApplicant_NonExistentApplicant() {
        //Save applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);

        //Save again to see if applicantRepository will update new applicant with different Id
        Applicant newApplicantSameID = new Applicant();
        newApplicantSameID.setId("TestID2");
        newApplicantSameID.setFirstName("Tom");
        newApplicantSameID.setLastName("Levine");
        newApplicantSameID.setState("TX");
        newApplicantSameID.setAge(26);
        newApplicantSameID.setGpa(3.1);
        newApplicantSameID.setGpaScale(5.0);
        newApplicantSameID.setSatScores(1754);
        newApplicantSameID.setActScores(21);
        newApplicantSameID.setFelonies(1);
        newApplicantSameID.setResult(Result.INSTANT_REJECT);
        newApplicantSameID.getResult().setReason("GPA is low");

        //Save new applicant with same Id
        applicantRepository.updateApplicant(newApplicantSameID.getId(), newApplicantSameID);

        //Retrieve applicant and check for difference in first and last name
        Applicant retrievedApplicant = applicantRepository.getApplicant("TestID");

        Assert.assertNotEquals(retrievedApplicant.getId(), newApplicantSameID.getId());
        Assert.assertNotEquals(retrievedApplicant.getFirstName(), newApplicantSameID.getFirstName());
        Assert.assertNotEquals(retrievedApplicant.getLastName(), newApplicantSameID.getLastName());
        Assert.assertNotEquals(retrievedApplicant.getState(), newApplicantSameID.getState());
        Assert.assertNotEquals(retrievedApplicant.getAge(), newApplicantSameID.getAge());
        Assert.assertNotEquals(retrievedApplicant.getGpa(), newApplicantSameID.getGpa());
        Assert.assertNotEquals(retrievedApplicant.getGpaScale(), newApplicantSameID.getGpaScale());
        Assert.assertNotEquals(retrievedApplicant.getSatScores(), newApplicantSameID.getSatScores());
        Assert.assertNotEquals(retrievedApplicant.getActScores(), newApplicantSameID.getActScores());
        Assert.assertNotEquals(retrievedApplicant.getFelonies(), newApplicantSameID.getFelonies());
        Assert.assertNotEquals(retrievedApplicant.getResult(), newApplicantSameID.getResult());
        Assert.assertNotEquals(retrievedApplicant.getResult().getReason(), newApplicantSameID.getResult().getReason());
    }

    @Test
    public void testUpdateApplicant_AlreadyExistApplicant() {
        //Save applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);

        //Save again to see if applicantRepository will update new applicant with same Id
        Applicant newApplicantSameID = new Applicant();
        newApplicantSameID.setId("TestID");
        newApplicantSameID.setFirstName("Tom");
        newApplicantSameID.setLastName("Levine");
        newApplicantSameID.setState("CA");
        newApplicantSameID.setAge(26);
        newApplicantSameID.setGpa(3.1);
        newApplicantSameID.setGpaScale(4.0);
        newApplicantSameID.setSatScores(1754);
        newApplicantSameID.setActScores(21);
        newApplicantSameID.setFelonies(1);
        newApplicantSameID.setResult(Result.FURTHER_REVIEW);
        newApplicantSameID.getResult().setReason("N/A");

        //Save new applicant with same Id
        applicantRepository.updateApplicant(newApplicantSameID.getId(), newApplicantSameID);

        //Retrieve applicant and check for difference in first and last name
        Applicant retrievedApplicant = applicantRepository.getApplicant("TestID");

        Assert.assertEquals(retrievedApplicant.getId(), newApplicantSameID.getId());
        Assert.assertEquals(retrievedApplicant.getFirstName(), newApplicantSameID.getFirstName());
        Assert.assertEquals(retrievedApplicant.getLastName(), newApplicantSameID.getLastName());
        Assert.assertEquals(retrievedApplicant.getState(), newApplicantSameID.getState());
        Assert.assertEquals(retrievedApplicant.getAge(), newApplicantSameID.getAge());
        Assert.assertEquals(retrievedApplicant.getGpa(), newApplicantSameID.getGpa());
        Assert.assertEquals(retrievedApplicant.getGpaScale(), newApplicantSameID.getGpaScale());
        Assert.assertEquals(retrievedApplicant.getSatScores(), newApplicantSameID.getSatScores());
        Assert.assertEquals(retrievedApplicant.getActScores(), newApplicantSameID.getActScores());
        Assert.assertEquals(retrievedApplicant.getFelonies(), newApplicantSameID.getFelonies());
        Assert.assertEquals(retrievedApplicant.getResult(), newApplicantSameID.getResult());
        Assert.assertEquals(retrievedApplicant.getResult().getReason(), newApplicantSameID.getResult().getReason());
    }

    @Test
    public void testDeleteApplicant_ValidId() {
        //Save applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);

        //Delete Applicant with valid Id
        boolean result = applicantRepository.deleteApplicant(applicant.getId());

        //Check result is true and Repository has no record of this applicant
        Assert.assertTrue(result);

        Applicant retrievedApplicant = applicantRepository.getApplicant(applicant.getId());
        Assert.assertEquals(retrievedApplicant.getId(), null);
        Assert.assertEquals(retrievedApplicant.getFirstName(), null);
        Assert.assertEquals(retrievedApplicant.getLastName(), null);
        Assert.assertEquals(retrievedApplicant.getState(), null);
        Assert.assertEquals(retrievedApplicant.getAge(), null);
        Assert.assertEquals(retrievedApplicant.getGpa(), null);
        Assert.assertEquals(retrievedApplicant.getGpaScale(), null);
        Assert.assertEquals(retrievedApplicant.getSatScores(), null);
        Assert.assertEquals(retrievedApplicant.getActScores(), null);
        Assert.assertEquals(retrievedApplicant.getFelonies(), null);
        Assert.assertEquals(retrievedApplicant.getResult(), null);
    }

    @Test
    public void testDeleteApplicant_InvalidId() {
        //Save applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);

        //Delete Applicant with invalid Id
        boolean result = applicantRepository.deleteApplicant("TestID2");

        //Check result is false and Repository still has a record of the saved applicant
        Assert.assertFalse(result);

        Applicant retrievedApplicant = applicantRepository.getApplicant(applicant.getId());
        Assert.assertEquals(retrievedApplicant.getId(), applicant.getId());
        Assert.assertEquals(retrievedApplicant.getFirstName(), applicant.getFirstName());
        Assert.assertEquals(retrievedApplicant.getLastName(), applicant.getLastName());
        Assert.assertEquals(retrievedApplicant.getState(), applicant.getState());
        Assert.assertEquals(retrievedApplicant.getAge(), applicant.getAge());
        Assert.assertEquals(retrievedApplicant.getGpa(), applicant.getGpa());
        Assert.assertEquals(retrievedApplicant.getGpaScale(), applicant.getGpaScale());
        Assert.assertEquals(retrievedApplicant.getSatScores(), applicant.getSatScores());
        Assert.assertEquals(retrievedApplicant.getActScores(), applicant.getActScores());
        Assert.assertEquals(retrievedApplicant.getFelonies(), applicant.getFelonies());
        Assert.assertEquals(retrievedApplicant.getResult(), applicant.getResult());
        Assert.assertEquals(retrievedApplicant.getResult().getReason(), applicant.getResult().getReason());
    }

    @Test
    public void testGetAllApplicant() {

        Applicant newApplicant = new Applicant();
        newApplicant.setId("TestID2");
        newApplicant.setFirstName("Tom");
        newApplicant.setLastName("Levine");
        newApplicant.setState("TX");
        newApplicant.setAge(26);
        newApplicant.setGpa(3.1);
        newApplicant.setGpaScale(5.0);
        newApplicant.setSatScores(1754);
        newApplicant.setActScores(21);
        newApplicant.setFelonies(1);
        newApplicant.setResult(Result.INSTANT_REJECT);
        newApplicant.getResult().setReason("GPA is low");

        //Save applicant
        applicantRepository.saveApplicant(applicant.getId(), applicant);
        applicantRepository.saveApplicant(newApplicant.getId(), newApplicant);

        //Retrieve all Applicant
        List<Applicant> allApplicants = applicantRepository.getAllApplicant();

        //Check that list has two applicants
        Assert.assertEquals(2, allApplicants.size());
    }
}
