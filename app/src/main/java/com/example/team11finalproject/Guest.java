package com.example.team11finalproject;

import java.util.ArrayList;

public class Guest {

    private String name;
    private ArrayList<String> restrictions;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Guest(ArrayList<String> restrictions, String name)
    {
        this.name = name;
        this.restrictions = restrictions;
    }

    public ArrayList<String> getRestrictions()
    {
        return restrictions;
    }

    public void setRestrictions(ArrayList<String> restrictions)
    {
        this.restrictions = restrictions;
    }
}
