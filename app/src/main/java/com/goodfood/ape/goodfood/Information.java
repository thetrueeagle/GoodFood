package com.goodfood.ape.goodfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Button btnFood = findViewById(R.id.foodSceneBtn);
        btnFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Information.this, FoodInfo.class);
                startActivity(intent);
            }
        });

        Button btnInfo = findViewById(R.id.appInfoBtn);
        btnInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Information.this, AppInfo.class);
                startActivity(intent);
            }
        });

        Button btnResearch = findViewById(R.id.researchInfoBtn);
        btnResearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Information.this, Research.class);
                startActivity(intent);
            }
        });
    }
}
