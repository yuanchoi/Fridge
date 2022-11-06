package com.teamfive.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        ImageView person = (ImageView) findViewById(R.id.person);
        person.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        ImageView cook = (ImageView) findViewById(R.id.cook);
        cook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CookActivity.class);
                startActivity(intent);
            }
        });

        ImageView add = (ImageView) findViewById(R.id.fridge);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent_add);
            }
        });

    }
}