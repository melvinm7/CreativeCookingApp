package com.example.team11finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeRecViewAdapter extends RecyclerView.Adapter<RecipeRecViewAdapter.ViewHolder>
{
    private static final String TAG = "RecipeRecViewAdapter";

    private ArrayList<Recipe> recipes = new ArrayList<>();      //the data set we use for the rec view
    private Context mContext;                       //the activity we want to pass the info to
    private String parentActivity;
    private String ing;


    public RecipeRecViewAdapter(Context mContext, String parentActivity)
    {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recipe, parent, false);
        return new RecipeRecViewAdapter.ViewHolder(view);        //creates and returns the view
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.txtRecipeName.setText(recipes.get(position).getDish());
        Recipe r = recipes.get(position);
        ing = String.join(", ", r.getIngredients());
        holder.txtIngredients.setText(ing);

    }

    @Override
    public int getItemCount()
    {
        return recipes.size();
    }

    public void setRecipes(ArrayList<Recipe> recipes)
    {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView parent;        //here we can make our variables
        private TextView txtRecipeName, txtIngredients;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtRecipeName = itemView.findViewById(R.id.txtRecipeName);
            txtIngredients = itemView.findViewById(R.id.txtIngredients);
        }
    }
}
