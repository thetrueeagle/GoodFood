package com.goodfood.ape.goodfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Login extends Activity {


    EditText email, pass;
    Button login;
    MyDBHandler db;
    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String PREFS_NAME = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.log_in_button);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE); //functions as session control?? doesn't work yet
        

        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                db = new MyDBHandler(Login.this, null, null, 1);
                String username = email.getText().toString();
                String password = pass.getText().toString();


                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill in your details!", Toast.LENGTH_LONG).show();
                } else {


                    if (db.checkPassword(username, password)) {    //checks if password matches the one in database

                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();


                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(PREFS_NAME, username);
                        editor.apply();

                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_LONG).show();
                        //email.setText("");
                        pass.setText("");
                    }


                }
            }
        });




    }

    /* @Override
                    public boolean onCreateOptionsMenu(Menu menu) {
MenuInflater inflater = getMenuInflater();
inflater.inflate(R.menu.action_settings, menu);
            return super.onCreateOptionsMenu(menu);
           //
     }
     */
   /* public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_settings:
                // search action
                Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
                return true;
        }
        return false;

    } */
                /*             @Override
                                protected void onResume() {
                                                // TODO Auto-generated method stub
                                                 list = db.getAllRegister();
                                //            list=((alertv) alert).showAlert();
                                                                Customlist adapter = new Customlist(MainActivity.this, list);
                                                //            mylistview.setAdapter(adapter);

                                                                if(adapter.getCount()!=0){
                                                                      mylistview.setAdapter(adapter);


                                                                }else{
                                                                     Toast.makeText(MainActivity.this, "No Items Available",Toast.LENGTH_SHORT).show();
                                                                }
                                                super.onResume();
                                  }
  */

}

