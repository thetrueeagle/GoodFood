package com.goodfood.ape.goodfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.marcok.stepprogressbar.StepProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class DailyIntake extends AppCompatActivity {



    private PrefManager prefManager;
    private TextView count, streak, title;
    private final String[] labels = {"1", "", "", "", "5", "", "7", "", "", "Champion"};
    private MyDBHandler db;
    private int lastUpdated, today;
    private StepProgressBar intakeBar;


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


        db = new MyDBHandler(DailyIntake.this, null, null, 1);
        prefManager = new PrefManager(DailyIntake.this);
        final Boolean dataColl = prefManager.getDataColl();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();
        final DataCollection dataCollWebService = retrofit.create(DataCollection.class);


        final String goal = prefManager.getGoal();


        if (prefManager.getDate()!=-1) { //checks if a stored date is present


            lastUpdated = prefManager.getDate();
            Calendar calendar = Calendar.getInstance();
            today = calendar.get(Calendar.DAY_OF_YEAR);


            if ((today - lastUpdated == 1)) { //checks if it is exactly the next day

                db.checkDateAndUpdate(true, goal); //strike maintained (if intake>=5)
                lastUpdated = today;
                prefManager.setDate(lastUpdated);

                //notify user badge earned + send to forms
                if(prefManager.getCode()) {
                    int streak = db.getDaysStreak();
                    if (streak == 1 || streak == 5 || streak == 10 || streak == 20 || streak == 50 || streak == 100) {
                        String uri = "@drawable/apple" + streak;  // where apple+streak is the file

                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                        Drawable res = getResources().getDrawable(imageResource);
                        new MaterialStyledDialog.Builder(this)
                                .setTitle("Awesome!")
                                .setDescription("Well done! You've earned a new badge!")
                                .setHeaderDrawable(res)
                                .withDialogAnimation(true)
                                .setPositiveText("OK!")
                                .show();

                        if (dataColl) { //checks if data collection is still active; only then send to forms
                            String email = db.getEmail();
                            Boolean code = prefManager.getCode();
                            String activity = "DAILY INTAKE STREAK BADGE";
                            String info = Integer.toString(streak);

                            Call<Void> sendDataCall = dataCollWebService.sendEngagement(email, code, activity, info);
                            sendDataCall.enqueue(callCallback);
                        }
                    }
                }



            } else if ((today - lastUpdated > 1)) { //checks if more than 1 day has passed
                db.checkDateAndUpdate(false, goal); //strike has been lost
                lastUpdated = today;
                prefManager.setDate(lastUpdated);


            }


        } else { //if no date present (this would be in the case when the section is opened for the first time

            Calendar calendar = Calendar.getInstance();
            int today = calendar.get(Calendar.DAY_OF_YEAR);

            prefManager.setDate(today);

        }


        if(prefManager.getCode()==true) {
            setContentView(R.layout.activity_daily_intake);


            count = findViewById(R.id.countSpinner);
            count.setText(Integer.toString(db.getDailyIntake()));
            streak = findViewById(R.id.streak);
            streak.setText(Integer.toString(db.getDaysStreak()));


            intakeBar = findViewById(R.id.intakeBar);
            intakeBar.setCurrentProgressDot(db.getDailyIntake()-1);
            intakeBar.setCumulativeDots(true);



            ImageButton btnAdd = findViewById(R.id.addButton);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (intakeBar.getCurrentProgressDot()==9) {

                    } else {

                        intakeBar.next();
                        int intake = intakeBar.getCurrentProgressDot()+1;

                        db.updateDailyIntake(intake);
                        db.logDailyIntake(intake, prefManager.getDate());
                        count.setText(Integer.toString(intake));

                        if(dataColl) {
                            String email = db.getEmail();
                            Boolean code = prefManager.getCode();
                            String activity = "DAILY INTAKE";
                            String info = Integer.toString(intake);

                            Call<Void> sendDataCall = dataCollWebService.sendEngagement(email, code, activity, info);
                            sendDataCall.enqueue(callCallback);
                        }



                        if(intakeBar.getCurrentProgressDot()==(Integer.parseInt(goal)-1)){
                            Toast.makeText(getApplicationContext(), "You've achieved your daily intake goal of "+goal+"!", Toast.LENGTH_LONG).show();
                        }
                        if(intakeBar.getCurrentProgressDot()==9){
                            Toast.makeText(getApplicationContext(), "You're a champion!", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            });


            ImageButton btnRemove = findViewById(R.id.removeButton);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (intakeBar.getCurrentProgressDot() ==-1) {

                    } else {

                        intakeBar.previous();
                        db.updateDailyIntake(intakeBar.getCurrentProgressDot()+1);
                        db.logDailyIntake(intakeBar.getCurrentProgressDot()+1, prefManager.getDate());
                        count.setText(Integer.toString(intakeBar.getCurrentProgressDot()+1));


                        if(intakeBar.getCurrentProgressDot()==(Integer.parseInt(goal)-1)){
                            Toast.makeText(getApplicationContext(), "You've achieved your daily intake goal of "+goal+"!", Toast.LENGTH_LONG).show();
                        }
                        if(intakeBar.getCurrentProgressDot()==9){
                            Toast.makeText(getApplicationContext(), "You're a champion!", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            });

        }
        if(prefManager.getCode()==false){
            setContentView(R.layout.activity_daily_intake_no);

            final Spinner count = findViewById(R.id.countSpinner);


            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.count, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            count.setAdapter(adapter);
            count.setSelection(db.getDailyIntake()-1);

            Button save = findViewById(R.id.save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int intake = Integer.parseInt(count.getSelectedItem().toString());
                    db.updateDailyIntake(intake);
                    db.logDailyIntake(intake, prefManager.getDate());

                    if(dataColl) {
                        String email = db.getEmail();
                        Boolean code = prefManager.getCode();
                        String activity = "DAILY INTAKE";
                        String info = Integer.toString(intake);

                        Call<Void> sendDataCall = dataCollWebService.sendEngagement(email, code, activity, info);
                        sendDataCall.enqueue(callCallback);
                    }



                    Toast.makeText(getApplicationContext(),
                            "Daily Intake saved!",
                            Toast.LENGTH_LONG)
                            .show();

                }
            });





        }



        title = findViewById(R.id.title5aday);
        title.setText(goal+"-a-day");




        Button changeGoal = findViewById(R.id.changeGoal);
        changeGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText edittext = new EditText(DailyIntake.this);
                edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
                edittext.setGravity(View.TEXT_ALIGNMENT_CENTER);
                edittext.setWidth(180);
                String uri = "@drawable/vegetables";

                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                new MaterialStyledDialog.Builder(DailyIntake.this)
                        .setTitle("Change goal")
                        .setDescription("What is your new daily intake goal?")
                        .setHeaderDrawable(res)
                        .withDialogAnimation(true)
                        .setPositiveText("Save")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                String newGoal = edittext.getText().toString();
                                prefManager.setGoal(newGoal);

                                Call<Void> sendDataCall = dataCollWebService.sendEngagement(db.getEmail(), prefManager.getCode(), "DAILY INTAKE GOAL CHANGE", newGoal);
                                sendDataCall.enqueue(callCallback);

                                // Refresh main activity upon close of dialog box
                                Intent refresh = new Intent(DailyIntake.this, DailyIntake.class);
                                startActivity(refresh);
                                finish(); //
                            }
                        })
                        .setNegativeText("Cancel")
                        .setCustomView(edittext)
                        .show();




            }
        });




        ImageButton btnInfo = findViewById(R.id.infoButton);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.nhs.uk/Livewell/5ADAY/Pages/Whatcounts.aspx"); // link to NHS page
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Button history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder dialog = new AlertDialog.Builder(DailyIntake.this);
                dialog.setTitle("Daily Intake History");
                dialog.setCancelable(true);


                view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                ListView list =  view.findViewById(R.id.intakeList);


                ArrayList<HashMap<String, String>> historyList = db.getHistory(); //get the list of 'done' recipes from database table

                ListAdapter adapter = new SimpleAdapter(
                        DailyIntake.this, historyList,
                        R.layout.list_item_2, new String[]{"date", "intake"}, new int[]{R.id.list_item_date, R.id.list_item_intake}); //populate list view with database data

                list.setAdapter(adapter);

                dialog.setCancelable(true);
                dialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });

                dialog.setView(view);
                dialog.show();


            }
        });


    }



}
