package com.teamfive.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseRecipe extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_recipe);


        ImageView cook = (ImageView) findViewById(R.id.cook);
        cook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CookActivity.class);
                startActivity(intent);
            }
        });

        ImageView soup = (ImageView) findViewById(R.id.soup);
        soup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SoupActivity.class);
                startActivity(intent);
            }
        });


    }
}
