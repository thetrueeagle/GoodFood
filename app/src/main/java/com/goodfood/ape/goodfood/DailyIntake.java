package com.goodfood.ape.goodfood;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;

public class DailyIntake extends AppCompatActivity {

    private NumberProgressBar bar;
    MyDBHandler db = new MyDBHandler(DailyIntake.this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_intake);
        bar = findViewById(R.id.number_progress_bar);
        bar.setSuffix("");
        bar.setMax(5);





        ImageButton btnAdd = findViewById(R.id.addButton);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(bar.getProgress()==5) {
                    Toast.makeText(getApplicationContext(), "You're already full!", Toast.LENGTH_LONG).show();
                }
                else {
                    bar.incrementProgressBy(1);
                    db.updateDailyIntake(bar.getProgress());
                }

            }
        });


        ImageButton btnRemove = findViewById(R.id.removeButton);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bar.getProgress()==0){
                    Toast.makeText(getApplicationContext(), "Can't go less than zero!", Toast.LENGTH_LONG).show();
                }
                else {
                    bar.setProgress(bar.getProgress() - 1);
                    db.updateDailyIntake(bar.getProgress());
                }
            }
        });



        Button btnLink = findViewById(R.id.link);
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.nhs.uk/Livewell/5ADAY/Pages/Whatcounts.aspx"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }


    public void addIntake(View view){




    }



}
