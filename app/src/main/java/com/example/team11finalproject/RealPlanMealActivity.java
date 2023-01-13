package com.example.team11finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RealPlanMealActivity extends AppCompatActivity {

    private RecyclerView RecipeRecView;
    private RecipeRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_plan_meal);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bar);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.nav_plan_button);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home_button:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_profiles_button:_button:
                        startActivity(new Intent(getApplicationContext(),ChloeTestsDPDatabase.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_plan_button:
                        return true;
                    case R.id.nav_community_button:
                        startActivity(new Intent(getApplicationContext(), RealCommmunityRecipeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //create adapter and rec view
        adapter = new RecipeRecViewAdapter(this, "planMealActivity");
        RecipeRecView = (RecyclerView) findViewById(R.id.planMealRecView);

        //set adapter and layout
        RecipeRecView.setAdapter(adapter);
        RecipeRecView.setLayoutManager(new LinearLayoutManager(this));

        //set the ingredients from the database
        adapter.setRecipes(Utils.getInstance(this).getApplicableRecipes(Utils.getInstance(this).getPantry()));
    }
}