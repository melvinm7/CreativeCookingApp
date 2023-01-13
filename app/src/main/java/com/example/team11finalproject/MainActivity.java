package com.example.team11finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<HomeScreenCard> mHomeCardData;
    private HomeScreenCardAdapter mAdapter;

    private ArrayList<Ingredient> pantry;
    private static final String TAG = "MyActivity";
    private Button btnIngredient, btnRecipeCalc, crapButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new HomeScreenCardAdapter(this, "mainActivity");

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setAdapter(mAdapter);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mHomeCardData = new ArrayList<>();
        // Get the data.
        initializeData();

        //setting the data to the adapter
        mAdapter.setHomeCards(mHomeCardData);

        //code just to see available recipes

    }


    private void initializeData() {
        // Get the resources from the XML file.
        String[] cardTitles = getResources()
                .getStringArray(R.array.card_titles);
        String[] cardInfo = getResources()
                .getStringArray(R.array.card_infos);

        TypedArray cardImageResources =
                getResources().obtainTypedArray(R.array.card_icons);

        // Clear the existing data (to avoid duplication).
        mHomeCardData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<cardTitles.length;i++){
            mHomeCardData.add(new HomeScreenCard(cardTitles[i],cardInfo[i],
                    cardImageResources.getResourceId(i,0)));
        }

        cardImageResources.recycle();



        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();




        Utils.getInstance(MainActivity.this);    //initializes all the data in Utils


    }
}