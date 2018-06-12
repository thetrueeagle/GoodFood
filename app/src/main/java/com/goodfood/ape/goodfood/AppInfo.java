package com.goodfood.ape.goodfood;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AppInfo extends AppCompatActivity {

    private PrefManager prefManager;
    public Boolean code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        code = prefManager.getCode();


        if(code==true) {
            setContentView(R.layout.activity_app_info);
        }
        if(code==false){
            setContentView(R.layout.activity_app_info_no);
        }

    }

    //each of the following methods corresponds to the onClick methods for the TextViews. These generate a dialog with some informative text

    public void dialogGeneral(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("General");
        if(code==true) {
            builder.setMessage(R.string.general);
        }
        if(code==false){
            builder.setMessage(R.string.general_no);
        }
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void dialogBags(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("Order Veg Bags");
        builder.setMessage(R.string.order);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void dialogRecipes(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("Recipes");
        if(code==true) {
            builder.setMessage(R.string.recipe);
        }
        if(code==false){
            builder.setMessage(R.string.recipe_no);
        }
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void dialogDailyIntake(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("Daily Intake");
        builder.setMessage(R.string.dailyIntake);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void dialogAchievements(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("Achievements");
        builder.setMessage(R.string.achieve);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void dialogInfo(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("Information");
        builder.setMessage(R.string.info);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void dialogFeedback(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("Feedback");
        builder.setMessage(R.string.feedback);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


    }






}
