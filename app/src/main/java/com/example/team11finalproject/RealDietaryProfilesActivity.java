package com.example.team11finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.team11finalproject.databinding.ActivityMainBinding;
import com.example.team11finalproject.databinding.ActivityRealDietaryProfilesBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealDietaryProfilesActivity extends AppCompatActivity {
    ActivityRealDietaryProfilesBinding binding;
    String FirstName;
    String LastName;
    int ProfileID;
    FirebaseDatabase db;

    private RecyclerView recyclerView;
    DPRecViewAdapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_dietary_profiles);

        //---------DATABASE POPULATION NONSENSE-------------
        mbase
                = FirebaseDatabase.getInstance().getReference();

        //recyclerView = findViewById(R.id.recycler1);



        //---------BOTTOM NAV NONSENSE-------------

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bar);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.nav_profiles_button);

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
                        return true;
                    case R.id.nav_plan_button:
                        startActivity(new Intent(getApplicationContext(),RealPlanMealActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_community_button:
                        startActivity(new Intent(getApplicationContext(), RealCommmunityRecipeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}