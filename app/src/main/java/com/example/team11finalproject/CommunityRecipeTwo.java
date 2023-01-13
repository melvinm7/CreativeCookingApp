package com.example.team11finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CommunityRecipeTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_recipe_two);

        TextView textView = (TextView) findViewById(R.id.prepVP);
        textView.setMovementMethod(new ScrollingMovementMethod());
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bar);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.nav_community_button);

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
                        startActivity(new Intent(getApplicationContext(),RealPlanMealActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_community_button:
                        return true;
                }
                return false;
            }
        });
    }
}