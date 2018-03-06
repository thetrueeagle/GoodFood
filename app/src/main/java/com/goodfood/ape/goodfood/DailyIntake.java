package com.goodfood.ape.goodfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import java.util.Calendar;


import static com.goodfood.ape.goodfood.Login.MyPREFERENCES;

public class DailyIntake extends AppCompatActivity {

    private NumberProgressBar bar;
    MyDBHandler db = new MyDBHandler(DailyIntake.this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_intake);


        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE); //gets the saved SharedPreferences file from storage
        if (sharedpreferences.contains("date")) { //checks if a stored date is present


            int lastUpdated = sharedpreferences.getInt("date", 0); //gets the stored date
            Calendar calendar = Calendar.getInstance();
            int today = calendar.get(Calendar.DAY_OF_YEAR);

            if ((today - lastUpdated == 1)) { //checks if it is exactly the next day

                db.checkDateAndUpdate(true); //strike maintained (if intake>=5)
                lastUpdated = today;
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putInt("date", lastUpdated);
                editor.apply();
            } else if ((today - lastUpdated > 1)) { //checks if more than 1 day has passed
                db.checkDateAndUpdate(false); //strike has been lost
                lastUpdated = today;
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putInt("date", lastUpdated);
                editor.apply();

            }


        } else { //if no date present (this would be in the case when the section is opened for the first time

            Calendar calendar = Calendar.getInstance();
            int today = calendar.get(Calendar.DAY_OF_YEAR);

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putInt("date", today);
            editor.apply();

        }


        bar = findViewById(R.id.number_progress_bar);
        bar.setSuffix("");
        bar.setMax(5);
        bar.setProgress(db.getDailyIntake());


        ImageButton btnAdd = findViewById(R.id.addButton);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (bar.getProgress() == 5) {
                    Toast.makeText(getApplicationContext(), "You're already full!", Toast.LENGTH_LONG).show();
                } else {
                    bar.incrementProgressBy(1);
                    db.updateDailyIntake(bar.getProgress());
                }

            }
        });


        ImageButton btnRemove = findViewById(R.id.removeButton);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bar.getProgress() == 0) {
                    Toast.makeText(getApplicationContext(), "Can't go less than zero!", Toast.LENGTH_LONG).show();
                } else {
                    bar.setProgress(bar.getProgress() - 1);
                    db.updateDailyIntake(bar.getProgress());
                }
            }
        });


        Button btnLink = findViewById(R.id.link);
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.nhs.uk/Livewell/5ADAY/Pages/Whatcounts.aspx"); // link to NHS page
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }


}
