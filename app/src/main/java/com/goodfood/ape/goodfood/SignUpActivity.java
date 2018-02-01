package com.goodfood.ape.goodfood;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {

    EditText first, last, email, pass, confpass;
    Button register;
    MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        first = findViewById(R.id.name);
        last = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        confpass = findViewById(R.id.confirmPassword);

        register = findViewById(R.id.register_button);


        register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub

                String edfirst = first.getText().toString();
                String edlast = last.getText().toString();
                String edemail = email.getText().toString();
                String edpass = pass.getText().toString();
                String edConf = confpass.getText().toString();

                if (edConf.equals(edpass)) {


                    db = new MyDBHandler(SignUpActivity.this, null, null, 1);

                    //encrypt pass method

                    Users user = new Users(edfirst, edlast, edemail, edpass);

                    db.addUser(user);

                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {

                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
                    pass.setText("");
                    confpass.setText("");
                }
            }
        });
    }


}