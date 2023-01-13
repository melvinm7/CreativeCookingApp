package com.example.team11finalproject;


import java.util.ArrayList;

public class Recipe
{
    //instead of a String array we could use a Array List of Ingredients. This way we could implement amounts and restrictions
    private ArrayList<String> ingredients;
    private String dish;
    private ArrayList<String> restrictions;

    public String getDish()
    {
        return dish;
    }

    public void setDish(String dish)
    {
        this.dish = dish;
    }

    public Recipe(ArrayList<String> ingredients, String dish, ArrayList<String> restrictions)
    {
        this.ingredients = ingredients;
        this.dish = dish;
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

    public ArrayList<String> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients)
    {
        this.ingredients = ingredients;
    }
}

