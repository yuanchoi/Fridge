package com.teamfive.fridge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeListAdapater extends RecyclerView.Adapter<RecipeListAdapater.MyViewHolder> {

    Context context;
    ArrayList<RecipeList> recipeListArrayList;

    public RecipeListAdapater(Context context, ArrayList<RecipeList> recipeListArrayList) {
        this.context = context;
        this.recipeListArrayList = recipeListArrayList;
    }

    @NonNull
    @Override
    public RecipeListAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recipe_list, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RecipeList recipeList = recipeListArrayList.get(position);

        holder.foodName.setText(recipeList.recipe_name);
        holder.foodImg.setImageResource(Integer.parseInt(recipeList.menu_img));
        



    }

    @Override
    public int getItemCount() {
        return recipeListArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView foodImg;
        TextView foodName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.recipe_name);
            foodImg = itemView.findViewById(R.id.menu_img);
        }
    }
}
