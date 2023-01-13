package com.example.team11finalproject;

class HomeScreenCard {
    //Data model for each row of the recylcer view for the HOME SCREEN cards

    private String cardTitle;
    private String cardInfo;
    private final int imageResource;


    public HomeScreenCard(String cardTitle, String cardInfo, int imageResource) {
        this.cardTitle = cardTitle;
        this.cardInfo = cardInfo;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return cardTitle;
    }

    String getInfo() {
        return cardInfo;
    }

    public int getImageResource() {
        return imageResource;
    }

}
