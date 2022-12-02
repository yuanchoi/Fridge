package com.teamfive.fridge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecipeListAdapater extends RecyclerView.Adapter<RecipeListAdapater.MyViewHolder> {

    Context context;
    ArrayList<RecipeList> recipeListArrayList;
    LinearLayout layout;

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

        Glide.with(holder.itemView)
                .load(recipeListArrayList.get(position).getMenu_img())
                .into(holder.foodImg);

        holder.foodImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(),LRecipeActivity.class);
                    intent.putExtra("name",recipeList.getRecipe_name());
                    intent.putExtra("img",recipeList.getMenu_img());
                    intent.putExtra("ingre",recipeList.getRecipe_ingre());
                    intent.putExtra("contents",recipeList.getRecipe_contents());
                    view.getContext().startActivity(intent);
                        }
                    });

        holder.foodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),LRecipeActivity.class);
                intent.putExtra("name",recipeList.getRecipe_name());
                intent.putExtra("img",recipeList.getMenu_img());
                intent.putExtra("ingre",recipeList.getRecipe_ingre());
                intent.putExtra("contents",recipeList.getRecipe_contents());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipeListArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView foodImg;
        TextView foodName;
        RecipeList list;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.recipe_name);
            foodImg = itemView.findViewById(R.id.menu_img);

                }
        }
    }
