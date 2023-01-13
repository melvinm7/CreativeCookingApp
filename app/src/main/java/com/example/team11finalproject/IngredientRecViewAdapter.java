package com.example.team11finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class IngredientRecViewAdapter extends RecyclerView.Adapter<IngredientRecViewAdapter.ViewHolder>
{
    private static final String TAG = "IngredientRecViewAdapter";

    private ArrayList<Ingredient> ingredients = new ArrayList<>();      //the data set we use for the rec view
    private Context mContext;                       //the activity we want to pass the info to
    private String parentActivity;


    public IngredientRecViewAdapter(Context mContext, String parentActivity)
    {
        this.mContext = mContext;
        this.parentActivity = parentActivity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ingredient, parent, false);
        return new ViewHolder(view);        //creates and returns the view
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: Called");     //just indicating when it is called

        //set name of the ingredient
        holder.txtName.setText(ingredients.get(position).getName());
        holder.txtMeasurement.setText(ingredients.get(position).getMeasurement());
        if(ingredients.get(position).getAmount() == 0)
            holder.txtAmount.setText("");
        else
            holder.txtAmount.setText(Integer.toString(ingredients.get(position).getAmount()));

        Glide.with(mContext)            //into the activity
                .asBitmap()
                .load(ingredients.get(position).getImageUrl())    //gets the url
                .into(holder.imgIngredient);                      //into the img view

        //code to adding an ingredients to the database, Right now its just a simple button with no prompt
        //Maybe add a add button to the cardview and use that to add
        if(parentActivity.equals("allIngredients"))
        {
            holder.edtAmount.setVisibility(View.VISIBLE);
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.btnAdd.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                   if(!holder.edtAmount.getText().toString().equals(""))
                   {
                       int amt = Integer.parseInt(holder.edtAmount.getText().toString());
                       if(Utils.getInstance(mContext).addIngredient(ingredients.get(holder.getAdapterPosition()), amt))
                       {
                           Toast.makeText(mContext, "Ingredient Added", Toast.LENGTH_SHORT).show();
                           notifyDataSetChanged();     //needed when data is changed
                       }
                       else
                           Toast.makeText(mContext, "Not Added", Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
        else if(parentActivity.equals("realpantryactivity"))
        {
            holder.btnRemove.setVisibility(View.VISIBLE);
            holder.btnRemove.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(Utils.getInstance(mContext).removeIngredient(ingredients.get(holder.getAdapterPosition())))
                    {
                        Toast.makeText(mContext, "Ingredient Removed", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();     //needed when data is changed
                    }
                    else
                        Toast.makeText(mContext, "Not Removed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return ingredients.size();
    }

    /**
     *     set the data we will be using, In this case it is the ingredients
     */
    public void setIngredients(ArrayList<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView parent;        //here we can make our variables
        private ImageView imgIngredient;
        private TextView txtName, txtMeasurement, txtAmount;
        private EditText edtAmount;
        private ImageButton btnAdd, btnRemove;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgIngredient = itemView.findViewById(R.id.imgIng);
            txtName = itemView.findViewById(R.id.txtName);
            txtMeasurement = itemView.findViewById(R.id.txtMeasurement);
            txtAmount = itemView.findViewById(R.id.txtAmount);

            edtAmount = itemView.findViewById(R.id.edtEnterAmt);
            btnAdd = itemView.findViewById(R.id.btnAddIngredient);
            btnRemove = itemView.findViewById(R.id.btnDelete);
        }
    }
}