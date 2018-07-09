package com.goodfood.ape.goodfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ape on 30/05/2018.
 */

public class Research extends AppCompatActivity {



    private static final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);

        }

        @Override
        public void onFailure(Call<Void> call, Throwable throwable) {
            Log.e("XXX", "Failed", throwable);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);

        final MyDBHandler db = new MyDBHandler(this, null, null, 0);
        final PrefManager prefManager = new PrefManager(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/")
                .build();
        final DataCollection dataCollWebService = retrofit.create(DataCollection.class);

        Button dropOut = findViewById(R.id.dropOutBtn);
        dropOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send data to us

                if (prefManager.getDataColl()) {

                    final String email = db.getEmail();
                    CheckBox consent = findViewById(R.id.consent);
                    final boolean check = consent.isChecked();

                    AlertDialog.Builder builder = new AlertDialog.Builder(Research.this);
                    builder.setCancelable(true);
                    builder.setTitle("Confirmation");
                    builder.setMessage("Are you sure you want to end participation in the research?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    NetworkCheck checkNetwork = new NetworkCheck(Research.this) {
                                        @Override
                                        public void onPostExecute(Boolean result) {
                                            if (result == false) {
                                                Toast.makeText(Research.this, "No internet connection. Cannot send data.. Please connect to the internet!", Toast.LENGTH_LONG).show();
                                            } else if (result) {
                                                //add variable collection

                                                if (check) {

                                                    Call<Void> sendDataCall = dataCollWebService.sendUserInfo("", "", email, 0, 0, 0, 0, 0, "The user has withdrawn consent to use their data");
                                                    sendDataCall.enqueue(callCallback);
                                                    prefManager.setDataColl(false);

                                                } else {
                                                    //get daily intake history from database and send to form
                                                    ArrayList<HashMap<String, String>> list = db.getDatabaseDailyIntake();

                                                    for (int i = 0; i < list.size(); i++) {

                                                        HashMap<String, String> day = list.get(i);
                                                        String date = day.get("date");
                                                        String intake = day.get("intake");

                                                        Call<Void> sendDataCall = dataCollWebService.sendDailyIntake(email, date, intake);
                                                        sendDataCall.enqueue(callCallback);


                                                    }

                                                    //get user info and send to form
                                                    String name = db.getName();
                                                    String surname = db.getSurname();
                                                    int streak = db.getDaysStreak();
                                                    int intake = db.getDailyIntake();
                                                    int recipes = db.getRecipeCount();
                                                    int orders = db.getOrderCount();
                                                    int badges = db.getBadgeCount();

                                                    Call<Void> sendDataCall = dataCollWebService.sendUserInfo(name, surname, email, streak, intake, recipes, orders, badges, "");
                                                    sendDataCall.enqueue(callCallback);


                                                    //get done and favourite recipes and send to form


                                                    //favorite
                                                    list = db.getDatabase(0);
                                                    for (int i = 0; i < list.size(); i++) {

                                                        HashMap<String, String> day = list.get(i);
                                                        String title = day.get("title");
                                                        String url = day.get("url");

                                                        sendDataCall = dataCollWebService.sendRecipes(email, title, url, "Favorite");
                                                        sendDataCall.enqueue(callCallback);
                                                    }

                                                    //done
                                                    list = db.getDatabase(1);
                                                    for (int i = 0; i < list.size(); i++) {

                                                        HashMap<String, String> day = list.get(i);
                                                        String title = day.get("title");
                                                        String url = day.get("url");

                                                        sendDataCall = dataCollWebService.sendRecipes(email, title, url, "Done");
                                                        sendDataCall.enqueue(callCallback);
                                                    }


                                                    prefManager.setDataColl(false);
                                                }





                                            }
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Research.this);
                                            builder.setCancelable(true);
                                            builder.setTitle("Thank you!");
                                            builder.setMessage("Thank you for participating! You can continue using the app, however data will no longer be collected. \nHappy eating!");
                                            builder.setPositiveButton("OK",
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                        }
                                                    });

                                            AlertDialog dialogEnd = builder.create();
                                            dialogEnd.show();


                                        }
                                    };

                                    checkNetwork.execute();
                                }

                            });

                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();


                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Research.this);
                    builder.setCancelable(true);
                    builder.setTitle("You have already submitted your data!");
                    builder.setMessage("You've already done this! Thank you for participating! You can continue using the app, however data is no longer collected. \nHappy eating!");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    AlertDialog dialogEnd = builder.create();
                    dialogEnd.show();

                }
            }

        });
    }







}
