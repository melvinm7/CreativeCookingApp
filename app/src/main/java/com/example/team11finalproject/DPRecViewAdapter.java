package com.example.team11finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class DPRecViewAdapter extends FirebaseRecyclerAdapter<DietaryProfile, DPRecViewAdapter.DPViewHolder> {
    private View.OnClickListener mOnItemClickListener;
    public View myItem;
    private Context mContext;

    private String stringFirstName;
    private String stringLastName;
    private String stringProfileID;

    private ArrayList<DietaryProfile> profiles = new ArrayList<>();

    public class myDPViewHolder extends RecyclerView.ViewHolder {

        public TextView firstNameTV;
        public TextView lastNameTV;
        public myDPViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameTV = itemView.findViewById(R.id.first_name);
            lastNameTV = itemView.findViewById(R.id.last_name);
            myItem = itemView.findViewById(R.id.main_rel_layout);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getContactTextView() {
            return firstNameTV;
        }
        public TextView getPhoneTextView() {
            return lastNameTV;
        }
    }


    public DPRecViewAdapter(
            @NonNull FirebaseRecyclerOptions<DietaryProfile> options, String FirstName, String LastName, String ProfileID, Context inputContext
    ){
        super(options);
        stringFirstName = FirstName;
        stringLastName = LastName;
        stringProfileID = ProfileID;
        mContext = inputContext;

    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull DPViewHolder holder,
                     int position, @NonNull DietaryProfile model)
    {
        String holdFirstName = model.getFirstName();
        String holdLastName = model.getLastName();
        String holdProfileID = model.getProfileID();
        String holdRestrictionList = model.getRestrictions().toString();

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.FirstName.setText(model.getFirstName());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.LastName.setText(model.getLastName());

        holder.ProfileID.setText(model.getProfileID());

        holder.RestrictionList.setText(model.getRestrictions().toString());

        holder.FirstName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent fintent = new Intent(holder.FirstName.getContext(), RealDietaryProfileComplete.class);
                fintent.putExtra("ProfileID", holdProfileID);
                fintent.putExtra("FirstName", holdFirstName);
                fintent.putExtra("LastName", holdLastName);
                fintent.putExtra("Restrictions", holdRestrictionList);
                System.out.println("first name on click - id extra is " + holdFirstName);
                holder.FirstName.getContext().startActivity(fintent);
            }
        });

        holder.LastName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent lintent = new Intent(holder.LastName.getContext(), RealDietaryProfileComplete.class);
                lintent.putExtra("ProfileID", holdProfileID);
                lintent.putExtra("FirstName", holdFirstName);
                lintent.putExtra("LastName", holdLastName);
                lintent.putExtra("Restrictions", holdRestrictionList);
                holder.LastName.getContext().startActivity(lintent);
            }
        });

    };



    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public DPViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dietaryprofile_list_item, parent, false);
        return new DPRecViewAdapter.DPViewHolder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class DPViewHolder
            extends RecyclerView.ViewHolder {
        TextView FirstName, LastName, RestrictionList, ProfileID;
        public DPViewHolder(@NonNull View itemView)
        {
            super(itemView);

            FirstName
                    = itemView.findViewById(R.id.first_name);
            LastName = itemView.findViewById(R.id.last_name);
            ProfileID = itemView.findViewById(R.id.profileID);
            RestrictionList = itemView.findViewById(R.id.dietary_restrictions_list_item);
        }
    }



}
