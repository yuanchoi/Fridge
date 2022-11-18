package com.teamfive.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

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


        ImageView calendar = (ImageView) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startSignUpActivity();
        }

        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startSignUpActivity();
                    break;
            }
        }
    };


    private void startSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}