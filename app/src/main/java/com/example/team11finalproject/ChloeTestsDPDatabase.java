package com.example.team11finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.team11finalproject.databinding.ActivityMainBinding;
import com.example.team11finalproject.databinding.ActivityRealDietaryProfilesBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChloeTestsDPDatabase extends AppCompatActivity {
    //this class is also messy but again. it does what it NEEDS TO DO so we are good to go i think actually -chloe
    ActivityRealDietaryProfilesBinding binding;
    String FirstName;
    String LastName;
    String ProfileID;
    Context context;
    FirebaseDatabase db;

    private FloatingActionButton newProfileButton;


    private RecyclerView recyclerView;
    DPRecViewAdapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    ArrayList<DietaryProfile> dietaryProfiles;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            String DPId = dietaryProfiles.get(position).getProfileID();
            Intent intent = new Intent(ChloeTestsDPDatabase.this, RealDietaryProfileComplete.class);
            intent.putExtra("ProfileID", ProfileID);
            intent.putExtra("FirstName", FirstName);
            intent.putExtra("LastName", LastName);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chloe_tests_dpdatabase);

        //---------DATABASE POPULATION NONSENSE-------------
        mbase
                = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.chloeTestRecView);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<DietaryProfile> options
                = new FirebaseRecyclerOptions.Builder<DietaryProfile>()
                .setQuery(mbase, DietaryProfile.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new DPRecViewAdapter(options, FirstName, LastName, ProfileID, context);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        //---------ADD NEW PROFILE BUTTON----------------------

        newProfileButton = findViewById(R.id.add_new_profile);

        newProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewDietaryProfile.class));
            }
        });

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

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }







}