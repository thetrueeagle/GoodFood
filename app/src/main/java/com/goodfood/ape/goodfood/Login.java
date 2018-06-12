package com.goodfood.ape.goodfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//no longer used
public class Login extends Activity {


    EditText email;
    Button login;
    MyDBHandler db;
    private PrefManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.email);
        login = findViewById(R.id.log_in_button);




        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) { //when Log in is clicked
                // TODO Auto-generated method stub
                db = new MyDBHandler(Login.this, null, null, 1);
                String username = email.getText().toString();



                if (username.equals("")) { //if fields are empty
                    Toast.makeText(getApplicationContext(), "Please fill in your details!", Toast.LENGTH_LONG).show();
                } else {


                    if (db.checkData(username)) {    //checks if password matches the one in database

                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();

                        prefManager = new PrefManager(Login.this);
                        prefManager.setName(db.getName());


                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_LONG).show();
                        email.setText("");

                    }


                }
            }
        });


    }

}