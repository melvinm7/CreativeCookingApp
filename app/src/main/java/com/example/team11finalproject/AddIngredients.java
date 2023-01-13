package com.example.team11finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddIngredients extends AppCompatActivity
{

    private RecyclerView IngredientsRecView;
    private IngredientRecViewAdapter adapter;
    private Spinner spinner;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        //create adapter and rec view
        adapter = new IngredientRecViewAdapter(this, "allIngredients");
        IngredientsRecView = (RecyclerView) findViewById(R.id.ingredientsRecView);

        //set adapter and layout
        IngredientsRecView.setAdapter(adapter);
        IngredientsRecView.setLayoutManager(new GridLayoutManager(this, 2));

        //set the ingredients from the database
        adapter.setIngredients(Utils.getInstance(this).getAllIngredients());

/*
        //spinner version to add items, quicker than rec view. We can use this if we don't have enough time
        spinner = (Spinner) findViewById(R.id.spnIngredients);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ingredients,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        //button to just add the item from the spinner to the database
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                */
/*if(Utils.getInstance(AddIngredients.this).addIngredient(spinner.getSelectedItem().toString()))
                {
                    Toast.makeText(AddIngredients.this, "Ingredient Added", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(AddIngredients.this, "Not Added", Toast.LENGTH_SHORT).show();*//*



            }
        });
*/

        // Initialize and assign variable
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bar);


        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.nav_profiles_button);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.nav_profiles_button);

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:     //will work the same as the bottom back button
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish()        //whenever the activity is finished
    {
        super.finish();
        //overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }
}