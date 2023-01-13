package com.example.team11finalproject;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeScreenCardAdapter extends RecyclerView.Adapter<HomeScreenCardAdapter.ViewHolder>{
    // Member variables.
    private ArrayList<HomeScreenCard> mHomeCardData;
    private Context mContext;
    private Bundle savedInstanceState;

    private String parentActivity;



    //just need Context for the constructor, the String is optional. Set the Data in a setter method
    HomeScreenCardAdapter(Context context,String parentActivity /*ArrayList<HomeScreenCard> homeCardData*/)
    {
        //this.mHomeCardData = homeCardData;
        this.mContext = context;
        this.parentActivity = parentActivity;
    }


    @Override
    public HomeScreenCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);        //creates and returns the view

 /*       return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));*/
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        // Get current sport.
        HomeScreenCard currentCard = mHomeCardData.get(position);

        // Populate the textviews with data.
        //holder.bindTo(currentCard);
        holder.mInfoText.setText(currentCard.getInfo());
        holder.mTitleText.setText(currentCard.getTitle());

        //image
        Glide.with(mContext).load(mHomeCardData.get(position).getImageResource()).into(holder.mCardImage);

        final int[] mPosition = new int[1];

        //this is where we are adding the on Click to take use to another class
        holder.mInfoText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //Right now I've just set the code to take us to the temporary AddIngredients class I made
                //There is something wrong with the other class such as the Pantry Activity that causes an error when we start it
                switch (holder.getAdapterPosition()){
                    case 0:
                        Intent intent0 = new Intent(mContext, RealPantryActivity.class);
                        mContext.startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(mContext, ChloeTestsDPDatabase.class);
                        mContext.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(mContext, RealPlanMealActivity.class);
                        mContext.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(mContext, RealCommmunityRecipeActivity.class);
                        mContext.startActivity(intent3);
                        break;
                    default:
                        break;

                }


                /*Activity currentActivity = ((MyApp)mContext.getApplicationContext()).getCurrentActivity();
                mPosition[0] = holder.getAdapterPosition();

                if(position==0){
                    Intent intent = new Intent(view.getContext(), PantryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(view.getContext(), intent, savedInstanceState);

                }else if(position==1){
                    Intent intent = new Intent (currentActivity, DietaryProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(view.getContext(), intent, savedInstanceState);
                }else if(position==2){
                    Intent intent = new Intent (currentActivity, PlanMealActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(view.getContext(), intent, savedInstanceState);
                }else if(position==3){
                    Intent intent = new Intent (currentActivity, CommunityRecipeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(view.getContext(), intent, savedInstanceState);
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHomeCardData.size();
    }

    //Use this to set the data to the adapter
    public void setHomeCards(ArrayList<HomeScreenCard> homecards)
    {
        this.mHomeCardData = homecards;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mCardImage;
        //private CardView parent;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            // Initialize the views.
            //parent = itemView.findViewById(R.id.parent);
            mTitleText = itemView.findViewById(R.id.card_title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mCardImage = itemView.findViewById(R.id.card_icon);

        }

        //Bind the code in the OnBind method instead

/*        void bindTo(HomeScreenCard currentCard){
            // Populate the text views with data.
            mTitleText.setText(currentCard.getTitle());
            mInfoText.setText(currentCard.getInfo());
            Glide.with(mContext).load(currentCard.getImageResource()).into(mCardImage);

        }*/
    }
}
