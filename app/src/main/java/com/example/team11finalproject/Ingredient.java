package com.example.team11finalproject;

public class Ingredient
{
    private int id, calories, amount;
    private String name, imageUrl, measurement;
    //Can add more variables


    public Ingredient(int id, String name, String measurement, String imageUrl)
    {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.imageUrl = imageUrl;
    }

    public Ingredient(int id, String name,String measurement, int amount, String imageUrl)
    {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.amount = amount;
        this.imageUrl = imageUrl;
    }

    public Ingredient(String name, int amount)
    {
        this.name = name;
        this.amount = amount;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCalories()
    {
        return calories;
    }

    public void setCalories(int calories)
    {
        this.calories = calories;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public String getMeasurement()
    {
        return measurement;
    }

    public void setMeasurement(String measurement)
    {
        this.measurement = measurement;
    }
}
