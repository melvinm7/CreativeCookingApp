package com.example.team11finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RealDietaryProfileComplete extends AppCompatActivity {
    FirebaseDatabase myDB;
    DatabaseReference myDBReference = myDB.getInstance().getReference();

    private DietaryProfile currentProfile;

    String FirstName;
    String LastName;
    ArrayList<String> Restrictions = new ArrayList<String>();
    String ProfileID;
    String RestrictionList;

    private FloatingActionButton backButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_dietary_profile_complete);

        TextView dpFirstName = findViewById(R.id.fp_first_name_text);
        TextView dpLastName = findViewById(R.id.fp_last_name_text);
        TextView dpListofRestrictions = findViewById(R.id.dietary_restriction_list);
        backButton = findViewById(R.id.back_to_profiles);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChloeTestsDPDatabase.class));
            }
        });
        System.out.println("on create");

        if (getIntent().hasExtra("ProfileID") && getIntent().hasExtra("FirstName") && getIntent().hasExtra("LastName")) {
            ProfileID = getIntent().getStringExtra("ProfileID");
            FirstName = getIntent().getStringExtra("FirstName");
            LastName = getIntent().getStringExtra("LastName");
            RestrictionList = getIntent().getStringExtra("Restrictions");
            System.out.println("intent has extras");
            System.out.println("profile id is " +ProfileID);
            System.out.println("first name is " +FirstName);
            System.out.println("last name is " +LastName);

            dpFirstName.setText(FirstName);
            dpLastName.setText(LastName);
            dpListofRestrictions.setText(RestrictionList);
            /*

            DatabaseReference firstNameReference = myDBReference.child(ProfileID).child("FirstName");
            DatabaseReference lastNameReference = myDBReference.child(ProfileID).child("LastName");
            DatabaseReference restrictionsReference = myDBReference.child(ProfileID).child("Restrictions");


             */
            myDBReference = FirebaseDatabase.getInstance().getReference();
            // replace below code

            /*
            if (ProfileID != null) {
                myDBReference = FirebaseDatabase.getInstance().getReference();

                myDBReference.child(ProfileID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild(ProfileID)) {
                                String FirstName = dataSnapshot.child(ProfileID).child("firstName").getValue().toString();
                                dpFirstName.setText(FirstName);
                            }
                            if (dataSnapshot.hasChild("LastName")) {
                                String LastName = dataSnapshot.child("LastName").getValue().toString();
                                dpLastName.setText(LastName);
                            }
                            if (dataSnapshot.hasChild("Restrictions")) {
                                ArrayList<String> myRestrictions = new ArrayList<String>();
                                String myRestrictionsStr;
                                myRestrictionsStr = dataSnapshot.child("Restrictions").getValue().toString();
                                dpListofRestrictions.setText(myRestrictionsStr);

                            } else {
                                Toast.makeText(RealDietaryProfileComplete.this, "Profile does not exist...", Toast.LENGTH_SHORT).show();
                            }


                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                backButton = findViewById(R.id.back_to_profiles);

             */





            /*

            firstNameReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String retrievedFirstName = dataSnapshot.getValue(String.class);
                    FirstName = retrievedFirstName;
                    currentProfile.setFirstName(FirstName);

                    dpFirstName = findViewById(R.id.fp_first_name_text);
                    dpFirstName.setText(currentProfile.getFirstName());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            lastNameReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String retrievedLastName = dataSnapshot.getValue(String.class);
                    LastName = retrievedLastName;
                    currentProfile.setFirstName(LastName);

                    dpLastName = findViewById(R.id.fp_last_name_text);
                    dpLastName.setText(currentProfile.getLastName());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            restrictionsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<String> retrievedRestrictions = dataSnapshot.getValue(ArrayList.class);
                    Restrictions = retrievedRestrictions;
                    currentProfile.setRestrictions(Restrictions);

                    dpListofRestrictions = findViewById(R.id.dietary_restriction_list);
                    dpListofRestrictions.setText(Restrictions.toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

             */

        }
    }
}