package com.goodfood.ape.goodfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class Home extends AppCompatActivity {

    MyDBHandler db;
    TextView welcome;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        welcome = findViewById(R.id.welcome);

        prefManager = new PrefManager(this);
        String name = prefManager.getName();


        welcome.setText("Welcome, " + name); //display welcome name


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.removePref("name");
                Toast.makeText(getApplicationContext(), "Successfully logged out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), StartScreen.class));
            }
        });


        Button btnOrderActivity = findViewById(R.id.order_veg_bag);
        btnOrderActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Order.class);
                startActivity(intent);
            }
        });

        Button btnRecipesActivity = findViewById(R.id.recipes);
        btnRecipesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, RecipeMenu.class);
                startActivity(intent);
            }
        });

        Button btnDailyIntake = findViewById(R.id.daily_intake);
        btnDailyIntake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(Home.this, DailyIntake.class);
                startActivity(intent);
            }
        });

        Button btnMyProfileActivity = findViewById(R.id.my_details);
        btnMyProfileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MyDetails.class);
                startActivity(intent);
            }
        });

        Button btnInformationActivity = findViewById(R.id.information);
        btnInformationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Information.class);
                startActivity(intent);
            }
        });

        Button btnAchievementsActivity = findViewById(R.id.achievements);
        btnAchievementsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Achievements.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed(){


    }


}
