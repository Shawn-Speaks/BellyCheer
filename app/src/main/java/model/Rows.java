package model;

import java.io.Serializable;

public class Rows implements Serializable {
    private String name;
    private String briefDescription;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private String phoneNumber;
    private String hoursOfOperation;
    private String intake;
    private String fee;
    private String features;
    private String eligibility;
    private String requiredDocuments;
    private String languages;

    public String getName() {
        return name;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public String getIntake() {
        return intake;
    }

    public String getFee() {
        return fee;
    }

    public String getFeatures() {
        return features;
    }

    public String getEligibility() {
        return eligibility;
    }

    public String getRequiredDocuments() {
        return requiredDocuments;
    }

    public String getLanguages() {
        return languages;
    }
}
