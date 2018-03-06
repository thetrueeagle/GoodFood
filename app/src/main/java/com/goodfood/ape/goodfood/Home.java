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

public class Home extends AppCompatActivity {

    MyDBHandler db;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);  //get sharedPreferences from storage

        welcome = findViewById(R.id.welcome);

        db = new MyDBHandler(Home.this, null, null, 1);


        welcome.setText("Welcome, " + db.getName()); //display welcome name


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("name");   //if logging out, delete sharedPreference key value entry
                editor.apply();
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

        Button btnMyProfileActivity = findViewById(R.id.my_profile);
        btnMyProfileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MyProfile.class);
                startActivity(intent);
            }
        });

        Button btnInformationActivity = findViewById(R.id.infromation);
        btnInformationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Information.class);
                startActivity(intent);
            }
        });


    }


}
