package com.goodfood.ape.goodfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    MyDBHandler dbHandler;
    TextView welcomeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This button will log you out", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button btnOrderActivity = (Button) findViewById(R.id.order_veg_bag);
        btnOrderActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchOrder();
            }
        });
        Button btnClassesActivity = (Button) findViewById(R.id.cooking_classes);
        btnClassesActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchClasses();
            }
        });

        Button btnRecipesActivity = (Button) findViewById(R.id.recipes);
        btnRecipesActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchRecipes();
            }
        });

        Button btnMyProfileActivity = (Button) findViewById(R.id.my_profile);
        btnMyProfileActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchMyProfile();
            }
        });

        Button btnInformationActivity = (Button) findViewById(R.id.infromation);
        btnInformationActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchInformation();
            }
        });


        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        welcomeName.setText(sharedpreferences.getString("nameKey", null));
    }



    private void launchOrder() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    private void launchClasses() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    private void launchRecipes() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    private void launchMyProfile() {
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

    private void launchInformation() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }



}
