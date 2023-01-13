package com.example.team11finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RealPantryActivity extends AppCompatActivity {

    private RecyclerView IngredientsRecView;
    private IngredientRecViewAdapter adapter;
    private Button btnIngredientPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_pantry);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bar);

        // Set Home selected
        //bottomNavigationView.setSelectedItemId(R.id.nav_profiles_button);

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
                        startActivity(new Intent(getApplicationContext(), RealCommmunityRecipeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //create adapter and rec view
        adapter = new IngredientRecViewAdapter(this, "realpantryactivity");
        IngredientsRecView = (RecyclerView) findViewById(R.id.pantry_recycler_view);

        //set adapter and layout
        IngredientsRecView.setAdapter(adapter);
        IngredientsRecView.setLayoutManager(new GridLayoutManager(this, 2));

        //set the ingredients from the database
        adapter.setIngredients(Utils.getInstance(this).getPantry());

        btnIngredientPage = (Button) findViewById(R.id.btnIngredientsPage);

        btnIngredientPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RealPantryActivity.this, AddIngredients.class);
                startActivity(intent);
            }
        });
    }
}