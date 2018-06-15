package com.goodfood.ape.goodfood;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

    EditText first, last, email, code, dailyIntake;
    Button register;
    MyDBHandler db;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
        builder.setCancelable(true);
        builder.setTitle("Sign Up Information");
        builder.setMessage("Welcome to the GoodFood app! \nThis app supports only one user from each device.\nIf you have already registered, please log in. \nYou will not be able to register another user.\nIf you are new to the app, please fill in your details, sign up and start using!");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


        first = findViewById(R.id.name);
        last = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        code = findViewById(R.id.userCode);
        dailyIntake = findViewById(R.id.dailyIntakeGoal);

        register = findViewById(R.id.register_button);
        db = new MyDBHandler(SignUp.this, null, null, 1);


        register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                if (db.checkDatabaseEmpty()) {

                    String edfirst = first.getText().toString();
                    String edlast = last.getText().toString();
                    String edemail = email.getText().toString();
                    String edcode = code.getText().toString();
                    String intakeGoal = dailyIntake.getText().toString();


                        Users user = new Users(edfirst, edlast, edemail, 0, 0, 0, 0);

                        db.addUser(user);
                        prefManager = new PrefManager(SignUp.this);
                        prefManager.setName(edfirst);
                        prefManager.setDataColl(true);
                        prefManager.setGoal(intakeGoal);
                        //depending on which password is entered, a boolean is set to true for gamified version and false for non-gamified. These values are later used to inflate different layouts.
                    //change the 'equals' to whatever passwords you set
                        if (edcode.equals("12345")) { //gamified
                        prefManager.setCode(true);
                            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Welcome.class));
                    }
                    else if(edcode.equals("54321")){ //non-gamified
                        prefManager.setCode(false);
                            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Welcome.class));
                    }
                    else{
                            Toast.makeText(getApplicationContext(), "Invalid code. try again!", Toast.LENGTH_LONG).show();
                        }


                    }

                else if(db.checkDatabaseEmpty()==false){
                    Toast.makeText(getApplicationContext(), "A user is already registered with this device. Please log in!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}