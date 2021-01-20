package com.college.applicant.classifier.CollegeApplicantClassifier.model;

public enum Result {
    INSTANT_REJECT("Instant Reject"),
    INSTANT_ACCEPT("Instant Accept"),
    FURTHER_REVIEW("Further Review");
    private String reason;

    private String result;

    Result(String result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
