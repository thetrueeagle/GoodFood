package com.goodfood.ape.goodfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void launchSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}

