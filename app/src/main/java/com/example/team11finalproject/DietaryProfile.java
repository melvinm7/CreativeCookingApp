package com.example.team11finalproject;

import java.util.ArrayList;
import java.util.List;

public class DietaryProfile {
    private String ProfileID;
    private String FirstName;
    private String LastName;
    private List<String> Restrictions=new ArrayList<String>();

    public DietaryProfile() {
        //empty constructor
    }

    public DietaryProfile(String profileID, String firstName, String lastName, ArrayList<String> restrictions) {
        ProfileID = profileID;
        FirstName = firstName;
        LastName = lastName;
        Restrictions = restrictions;
    }

    public String getProfileID() {
        return ProfileID;
    }

    public void setProfileID(String profileID) {
        ProfileID = profileID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public List<String> getRestrictions() {
        return Restrictions;
    }

    public void setRestrictions(List<String> restrictions) {
        Restrictions = restrictions;
    }
}
