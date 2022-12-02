package com.teamfive.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class LRecipeActivity extends AppCompatActivity {
    private Intent intent;
    private String name, img, ingre, contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipelast_main);

        RecipeList list = (RecipeList) getIntent().getSerializableExtra("list");
        intent = getIntent();

        img = intent.getStringExtra("img");           //이미지
        ingre = intent.getStringExtra("ingre");       //재료
        contents = intent.getStringExtra("contents"); //레시피


        try{
            ImageView foodImg = findViewById(R.id.menu_img);
            TextView foodIngre = findViewById(R.id.recipe_ingre);
            TextView foodContents = findViewById(R.id.recipe_contents);

            Glide.with(this)
                    .load(img)
                    .into(foodImg);
            foodIngre.setText(ingre);
            foodContents.setText(contents);

        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        }

}

