package com.goodfood.ape.goodfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);





     Button btnAchievementsActivity = findViewById(R.id.achievements);
        btnAchievementsActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(MyProfile.this, Achievements.class);
                startActivity(intent);
            }
        });


        Button btnDailyIntake = findViewById(R.id.daily_intake);
        btnDailyIntake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(MyProfile.this, DailyIntake.class);
                startActivity(intent);
            }
        });

        Button btnMyDetails = findViewById(R.id.my_details);
        btnMyDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MyProfile.this, MyDetails.class);
                startActivity(intent);
            }
        });

    }



}
