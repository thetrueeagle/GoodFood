package com.goodfood.ape.goodfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.goodfood.ape.goodfood.Login.MyPREFERENCES;
import static com.goodfood.ape.goodfood.Login.PREFS_NAME;

public class Home extends AppCompatActivity {

    MyDBHandler db;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedpreferences.edit();
        welcome = findViewById(R.id.welcome);

        db = new MyDBHandler(Home.this, null, null, 1);


        welcome.setText("Welcome, "+ db.getName(sharedpreferences.getString(PREFS_NAME, "")));



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Successfully logged out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), StartScreen.class));
            }
        });


        Button btnOrderActivity = findViewById(R.id.order_veg_bag);
        btnOrderActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchOrder();
            }
        });
        Button btnClassesActivity = findViewById(R.id.cooking_classes);
        btnClassesActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchClasses();
            }
        });

        Button btnRecipesActivity = findViewById(R.id.recipes);
        btnRecipesActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchRecipes();
            }
        });

        Button btnMyProfileActivity = findViewById(R.id.my_profile);
        btnMyProfileActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchMyProfile();
            }
        });

        Button btnInformationActivity = findViewById(R.id.infromation);
        btnInformationActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchInformation();
            }
        });



    }



    private void launchOrder() {
        Intent intent = new Intent(this, Order.class);
        startActivity(intent);
    }

    private void launchClasses() {
        Intent intent = new Intent(this, Order.class);
        startActivity(intent);
    }

    private void launchRecipes() {
        Intent intent = new Intent(this, RecipeMenu.class);
        startActivity(intent);
    }

    private void launchMyProfile() {
        Intent intent = new Intent(this, MyProfile.class);
        startActivity(intent);
    }

    private void launchInformation() {
        Intent intent = new Intent(this, Order.class);
        startActivity(intent);
    }



}
