package com.goodfood.ape.goodfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Drop down menu
        Spinner spinner = findViewById(R.id.selectBags);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.order_amount, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        //Log out button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Create button activity
        Button btnSubmitActivity = findViewById(R.id.submit);
        btnSubmitActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                String weekDay;
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

                Calendar calendar = Calendar.getInstance();
                weekDay = dayFormat.format(calendar.getTime());
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                if((weekDay.equals("Tuesday")&&currentHour<17)||(weekDay.equals("Wednesday"))||(weekDay.equals("Thursday"))||(weekDay.equals("Friday"))||(weekDay.equals("Saturday"))||(weekDay.equals("Sunday"))){                  //Implement further to restrict orders after Tuesday 5pm

                    Toast.makeText(getApplicationContext(),
                            "Orders are not accepted today, try again on Monday :)",
                            Toast.LENGTH_LONG)
                            .show();
                }
                else {


                    Spinner bags = findViewById(R.id.selectBags);
                    final String amount = bags.getSelectedItem().toString();
                    EditText commentBox = findViewById(R.id.comment);
                    final String comment = commentBox.getText().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(Order.this);
                    builder.setCancelable(true);
                    builder.setTitle("Confirm order");
                    builder.setMessage("Are you sure you want to order?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    submitOrder(amount, comment);
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

            }
        });



    }

    private void submitOrder(String bags, String comment) {

        String emailBody = "Hello! \nI would like to order "+bags+" vegetable bag(s). \n"+comment+"\nThanks in advance!";


        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Veg Bag Order");
        intent.putExtra(Intent.EXTRA_TEXT, emailBody);
        intent.setData(Uri.parse("mailto:annap.eglite@gmail.com")); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);




    }

}
