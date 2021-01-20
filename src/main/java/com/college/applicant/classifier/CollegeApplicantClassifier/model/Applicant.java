package com.college.applicant.classifier.CollegeApplicantClassifier.model;

import lombok.Data;

@Data
public class Applicant {
    private String id;
    private String state;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double gpa;
    private Double gpaScale;
    private Integer satScores;
    private Integer actScores;
    private Integer felonies;
    private Integer yearOfRecentFelony;
    private Result result;
}
