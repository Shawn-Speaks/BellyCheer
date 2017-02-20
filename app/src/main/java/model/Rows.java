package model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Rows implements Serializable {
    private String name;
    private String briefdescription;
    private String streetaddress;
    private String city;
    private String state;
    private int zipcode;
    private String phonenumber;
    private String hoursofoperation;
    private String intake;
    private String fee;
    private String features;
    private String eligibility;
    private String requireddocuments;
    private String languages;
    private LatLng latLng;

    public String getName() {
        return name;
    }

    public String getBriefdescription() {
        return briefdescription;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getHoursofoperation() {
        return hoursofoperation;
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

    public String getRequireddocuments() {
        return requireddocuments;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
