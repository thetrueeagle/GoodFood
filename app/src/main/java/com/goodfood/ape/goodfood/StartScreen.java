package com.goodfood.ape.goodfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.goodfood.ape.goodfood.Login.MyPREFERENCES;

public class StartScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedpreferences.contains("name"))
        {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }

        Button btnLogInActivity = (Button) findViewById(R.id.log_in_button);
        btnLogInActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            launchLogIn();
            }
        });


        Button btnSignUpActivity = (Button) findViewById(R.id.sign_up_button);
        btnSignUpActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchSignUp();
            }
        });
    }




    private void launchLogIn() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void launchSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}

