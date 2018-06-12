package com.goodfood.ape.goodfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class StartScreen extends AppCompatActivity {

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        prefManager = new PrefManager(this);
        String name = prefManager.getName();
        if(!name.equals(""))
        {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }

        Button btnLogInActivity = findViewById(R.id.log_in_button);
        btnLogInActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Login.class);
                startActivity(intent);
            }
        });


        Button btnSignUpActivity = findViewById(R.id.sign_up_button);
        if(db.checkDatabaseEmpty()==false){
            btnSignUpActivity.setEnabled(false);
            btnSignUpActivity.setBackgroundColor(Color.parseColor("#66595959"));
        }
        btnSignUpActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(StartScreen.this, SignUp.class);
                startActivity(intent);
            }
        });
    }


}

