package com.goodfood.ape.goodfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Log out", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


     Button btnAchievementsActivity = findViewById(R.id.achievements);
        btnAchievementsActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchAchievements();
            }
        });

    }
    private void launchAchievements () {
        Intent intent = new Intent(this, AchievementsActivity.class);
        startActivity(intent);
    }


}
