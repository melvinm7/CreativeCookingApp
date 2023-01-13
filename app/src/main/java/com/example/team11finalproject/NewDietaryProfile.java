package com.example.team11finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.team11finalproject.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewDietaryProfile extends AppCompatActivity {
    //okay SO!!! this class is kind of a mess i think but it works and that is what MATTERS for now
    //thank you for coming... enjoy my comments -chloe

    //----------general database declarations----------------
    ActivityMainBinding binding;
    String FirstName;
    String LastName;
    String ProfileID;
    ArrayList<String> Restrictions=new ArrayList<String>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //--------make the darn strings----------------------
    String veganString = "Vegan";
    String vegetarianString = "Vegetarian";
    String shellfishAllergyString = "Shellfish allergy";
    String diabeticString = "Diabetic";
    String dislikesShellfishString = "Dislikes shellfish";
    String lactoseIntolerantString = "Lactose intolerant";
    String glutenIntolerantString = "Gluten intolerant";
    String peanutAllergyString = "Peanut allergy";
    String treeNutAllergyString = "Tree nut allergy";
    String dislikesRedMeatString = "Dislikes red meat";

    //--------edit text and friends declarations----------
    private EditText firstNameInput, lastNameInput;
    private FloatingActionButton saveRestrictionsButton;
    private FloatingActionButton backToProfilesButton;

    //---------declare the silly goose dietary profile class-------
    //sorry i'm really tired so my comments are a little weird i think
    //anyway
    DietaryProfile newProfile;

    //----------declare all the silly little buttons
    private Button vegetarianGreyButton;
    private Button vegetarianGreenButton;
    private Button veganGreyButton;
    private Button veganGreenButton;
    private Button shellfishAllergyGreyButton;
    private Button shellfishAllergyGreenButton;
    private Button diabeticGreyButton;
    private Button diabeticGreenButton;
    private Button dislikesShellfishGreyButton;
    private Button dislikesShellfishGreenButton;
    private Button lactoseIntolerantGreyButton;
    private Button lactoseIntolerantGreenButton;
    private Button glutenIntolerantGreyButton;
    private Button glutenIntolerantGreenButton;
    private Button peanutAllergyGreyButton;
    private Button peanutAllergyGreenButton;
    private Button treeNutAllergyGreyButton;
    private Button treeNutAllergyGreenButton;
    private Button dislikesRedMeatGreyButton;
    private Button dislikesRedMeatGreenButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dietary_profile);

        //initialize the edit text stuff
        firstNameInput = findViewById(R.id.first_name_text);
        lastNameInput = findViewById(R.id.last_name_text);

        //get instance of firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //get database reference
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://creative-cooking-dd723-default-rtdb.firebaseio.com/");

        //it's gonna be a new profile
        newProfile = new DietaryProfile();

        //button
        saveRestrictionsButton = findViewById(R.id.save_restrictions3);


        // adding on click listener for our button.
        saveRestrictionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String FirstName = firstNameInput.getText().toString();
                String LastName = lastNameInput.getText().toString();


                // below line is for checking whether the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(FirstName) && TextUtils.isEmpty(LastName)) {
                    // if the text fields are empty
                    // then show the below message.
                        Toast.makeText(NewDietaryProfile.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(ProfileID, FirstName, LastName, Restrictions);

                    //clear all data for a new entry
                    clearData(firstNameInput, lastNameInput, Restrictions, FirstName, LastName);

                    //make all the buttons grey so they can be used again
                    makeButtonGrey(veganGreyButton, veganGreenButton);
                    makeButtonGrey(vegetarianGreyButton, vegetarianGreenButton);
                    makeButtonGrey(shellfishAllergyGreyButton, shellfishAllergyGreenButton);
                    makeButtonGrey(diabeticGreyButton, diabeticGreenButton);
                    makeButtonGrey(dislikesShellfishGreyButton, dislikesShellfishGreenButton);
                    makeButtonGrey(lactoseIntolerantGreyButton, lactoseIntolerantGreenButton);
                    makeButtonGrey(glutenIntolerantGreyButton, glutenIntolerantGreenButton);
                    makeButtonGrey(peanutAllergyGreyButton, peanutAllergyGreenButton);
                    makeButtonGrey(treeNutAllergyGreyButton, treeNutAllergyGreenButton);
                    makeButtonGrey(dislikesRedMeatGreyButton, dislikesRedMeatGreenButton);


                }
            }
        });

        //~~~~~~~~~back to profiles button~~~~~~~~~
        backToProfilesButton = findViewById(R.id.back_to_profiles);

        backToProfilesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChloeTestsDPDatabase.class));
            }

        });


        //---------------------GET READY FOR A HUNDRED ONCLICK LISTENERS-------------------------

        //~~~~~~~vegetarian button~~~~~~~~~
        vegetarianGreyButton = findViewById(R.id.vegetarian_button_grey);
        vegetarianGreenButton = findViewById(R.id.vegetarian_button_green);

        vegetarianGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(vegetarianGreyButton.getVisibility() == vegetarianGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(vegetarianString);
                    //fix which button is visible
                    vegetarianGreyButton.setVisibility(v.INVISIBLE);
                    vegetarianGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        vegetarianGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(vegetarianGreenButton.getVisibility() == vegetarianGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(vegetarianString);
                    //fix which button is visible
                    vegetarianGreenButton.setVisibility(v.INVISIBLE);
                    vegetarianGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~vegan button~~~~~~~~~
        veganGreyButton = findViewById(R.id.vegan_button_grey);
        veganGreenButton = findViewById(R.id.vegan_button_green);

        veganGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(veganGreyButton.getVisibility() == veganGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(veganString);
                    //fix which button is visible
                    veganGreyButton.setVisibility(v.INVISIBLE);
                    veganGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        veganGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(veganGreenButton.getVisibility() == veganGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(veganString);
                    //fix which button is visible
                    veganGreenButton.setVisibility(v.INVISIBLE);
                    veganGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~shellfish allergy button~~~~~~~~~
        shellfishAllergyGreyButton = findViewById(R.id.shellfish_allergy_button_grey);
        shellfishAllergyGreenButton = findViewById(R.id.shellfish_allergy_button_green);

        shellfishAllergyGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(shellfishAllergyGreyButton.getVisibility() == shellfishAllergyGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(shellfishAllergyString);
                    //fix which button is visible
                    shellfishAllergyGreyButton.setVisibility(v.INVISIBLE);
                    shellfishAllergyGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        shellfishAllergyGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(shellfishAllergyGreenButton.getVisibility() == shellfishAllergyGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(shellfishAllergyString);
                    //fix which button is visible
                    shellfishAllergyGreenButton.setVisibility(v.INVISIBLE);
                    shellfishAllergyGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~diabetic button~~~~~~~~~
        diabeticGreyButton = findViewById(R.id.diabetic_button_grey);
        diabeticGreenButton = findViewById(R.id.diabetic_button_green);

        diabeticGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(diabeticGreyButton.getVisibility() == diabeticGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(diabeticString);
                    //fix which button is visible
                    diabeticGreyButton.setVisibility(v.INVISIBLE);
                    diabeticGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        diabeticGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(diabeticGreenButton.getVisibility() == diabeticGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(diabeticString);
                    //fix which button is visible
                    diabeticGreenButton.setVisibility(v.INVISIBLE);
                    diabeticGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~vegan button~~~~~~~~~
        dislikesShellfishGreyButton = findViewById(R.id.dislikes_shellfish_button_grey);
        dislikesShellfishGreenButton = findViewById(R.id.dislikes_shellfish_button_green);

        dislikesShellfishGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(dislikesShellfishGreyButton.getVisibility() == dislikesShellfishGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(dislikesShellfishString);
                    //fix which button is visible
                    dislikesShellfishGreyButton.setVisibility(v.INVISIBLE);
                    dislikesShellfishGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        dislikesShellfishGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(dislikesShellfishGreenButton.getVisibility() == dislikesShellfishGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(dislikesShellfishString);
                    //fix which button is visible
                    dislikesShellfishGreenButton.setVisibility(v.INVISIBLE);
                    dislikesShellfishGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~lactose intolerant button~~~~~~~~~
        lactoseIntolerantGreyButton = findViewById(R.id.lactose_intolerant_button_grey);
        lactoseIntolerantGreenButton = findViewById(R.id.lactose_intolerant_button_green);

        lactoseIntolerantGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(lactoseIntolerantGreyButton.getVisibility() == lactoseIntolerantGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(lactoseIntolerantString);
                    //fix which button is visible
                    lactoseIntolerantGreyButton.setVisibility(v.INVISIBLE);
                    lactoseIntolerantGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        lactoseIntolerantGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(lactoseIntolerantGreenButton.getVisibility() == lactoseIntolerantGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(lactoseIntolerantString);
                    //fix which button is visible
                    lactoseIntolerantGreenButton.setVisibility(v.INVISIBLE);
                    lactoseIntolerantGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~vegan button~~~~~~~~~
        glutenIntolerantGreyButton = findViewById(R.id.gluten_intolerant_button_grey);
        glutenIntolerantGreenButton = findViewById(R.id.gluten_intolerant_button_green);

        glutenIntolerantGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(glutenIntolerantGreyButton.getVisibility() == glutenIntolerantGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(glutenIntolerantString);
                    //fix which button is visible
                    glutenIntolerantGreyButton.setVisibility(v.INVISIBLE);
                    glutenIntolerantGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        glutenIntolerantGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(glutenIntolerantGreenButton.getVisibility() == glutenIntolerantGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(glutenIntolerantString);
                    //fix which button is visible
                    glutenIntolerantGreenButton.setVisibility(v.INVISIBLE);
                    glutenIntolerantGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~peanut allergy button~~~~~~~~~
        peanutAllergyGreyButton = findViewById(R.id.peanut_allergy_button_grey);
        peanutAllergyGreenButton = findViewById(R.id.peanut_allergy_button_green);

        peanutAllergyGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(peanutAllergyGreyButton.getVisibility() == peanutAllergyGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(peanutAllergyString);
                    //fix which button is visible
                    peanutAllergyGreyButton.setVisibility(v.INVISIBLE);
                    peanutAllergyGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        peanutAllergyGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(peanutAllergyGreenButton.getVisibility() == peanutAllergyGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(peanutAllergyString);
                    //fix which button is visible
                    peanutAllergyGreenButton.setVisibility(v.INVISIBLE);
                    peanutAllergyGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~tree nut allergy button~~~~~~~~~
        treeNutAllergyGreyButton = findViewById(R.id.tree_nut_allergy_button_grey);
        treeNutAllergyGreenButton = findViewById(R.id.tree_nut_allergy_button_green);

        treeNutAllergyGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(treeNutAllergyGreyButton.getVisibility() == treeNutAllergyGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(treeNutAllergyString);
                    //fix which button is visible
                    treeNutAllergyGreyButton.setVisibility(v.INVISIBLE);
                    treeNutAllergyGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        treeNutAllergyGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(treeNutAllergyGreenButton.getVisibility() == treeNutAllergyGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(treeNutAllergyString);
                    //fix which button is visible
                    treeNutAllergyGreenButton.setVisibility(v.INVISIBLE);
                    treeNutAllergyGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });

        //~~~~~~~dislikes red meat button~~~~~~~~~
        dislikesRedMeatGreyButton = findViewById(R.id.dislikes_red_meat_button_grey);
        dislikesRedMeatGreenButton = findViewById(R.id.dislikes_red_meat_button_green);

        dislikesRedMeatGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(dislikesRedMeatGreyButton.getVisibility() == dislikesRedMeatGreyButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.add(dislikesRedMeatString);
                    //fix which button is visible
                    dislikesRedMeatGreyButton.setVisibility(v.INVISIBLE);
                    dislikesRedMeatGreenButton.setVisibility(v.VISIBLE);
                }
            }
        });

        dislikesRedMeatGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this version of the button is currently visible
                if(dislikesRedMeatGreenButton.getVisibility() == dislikesRedMeatGreenButton.VISIBLE){
                    //add the correct string to the list
                    Restrictions.remove(dislikesRedMeatString);
                    //fix which button is visible
                    dislikesRedMeatGreenButton.setVisibility(v.INVISIBLE);
                    dislikesRedMeatGreyButton.setVisibility(v.VISIBLE);
                }
            }
        });
    }

    private void addDatatoFirebase(String ProfileID, String FirstName, String LastName, ArrayList<String> Restrictions) {
        // below 3 lines of code is used to set
        // data in our object class.
        String randomString = getAlphaNumericString(9);

        newProfile.setFirstName(FirstName);
        newProfile.setLastName(LastName);
        newProfile.setRestrictions(Restrictions);

        databaseReference.child(randomString).setValue(newProfile);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.

                // after adding this data we are showing toast message.
                Toast.makeText(NewDietaryProfile.this, "New profile added!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(NewDietaryProfile.this, "Failed to add new data :(" + error, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void clearData(EditText firstNameInput, EditText lastNameInput, ArrayList <String> Restrictions, String FirstName, String LastName){
        firstNameInput.setText("");
        lastNameInput.setText("");
        FirstName = "";
        LastName = "";
        Restrictions.clear();

    }

    private void makeButtonGrey(Button greyButton, Button greenButton){
        if(greenButton.getVisibility() == View.VISIBLE){
            greenButton.setVisibility(View.INVISIBLE);
            greyButton.setVisibility(View.VISIBLE);
        }
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}