package com.goodfood.ape.goodfood;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import static android.app.PendingIntent.getActivity;

public class Recipes extends AppCompatActivity {

    private View view;
    private int ingredients;
    private int temp;
    private String temp1;
    private String[] dietLabels = new String[6];
    private String[] healthLabels = new String[27];
    private int calorieMin;
    private int calorieMax;
    private String finalUrl;
    private String keyword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Button btnSearch = findViewById(R.id.searchButton);
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //add variable collection
                EditText keywordField = findViewById(R.id.keywordField);
                keyword = keywordField.getText().toString();
                finalUrl=generateURL(keyword, ingredients, dietLabels, healthLabels, calorieMin, calorieMax);
                Intent intent = new Intent(Recipes.this, ResultList.class);
                intent.putExtra("URL", finalUrl);
                startActivity(intent);
            }
        });


    }




    public void selectIngredientNo(View view){


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.ingredients_title)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ingredients = temp+1;
                        TextView maxIngredients = findViewById(R.id.maxField);
                        maxIngredients.setText("Max number of ingredients - "+ ingredients);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setSingleChoiceItems(R.array.count, -1, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item

                        temp = which;
                    }
                });









        ;

        // 3. Get the AlertDialog from create()
        builder.show();


    }

    public void selectDietaryOptions(View view){


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dietary_title)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });
                builder.setMultiChoiceItems(R.array.dietary_options, null, new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        //implement
                        if (b==true){
                            dietLabels[i]=getResources().getStringArray(R.array.dietary_options)[i];
                        }
                        if(b==false){
                            dietLabels[i]="";
                        }
                    }


                });


        // 3. Get the AlertDialog from create()
        builder.show();



    }


    public void selectHealthOptions(View view){


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.health_title)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
                .setMultiChoiceItems(R.array.health_options, null,  new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        //implement

                        if (b==true){
                            healthLabels[i]=getResources().getStringArray(R.array.health_options)[i];
                        }
                        if(b==false){
                            healthLabels[i]="";
                        }
                    }

                });


        // 3. Get the AlertDialog from create()
        builder.show();



    }



    public void selectCalorieRange(View view){


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.calorie_title);



        // Setup the new range seek bar
        final RangeSeekBar<Integer> rangeSeekBar = new RangeSeekBar<Integer>(this);
        // Set the range
        rangeSeekBar.setRangeValues(0, 1000);
        rangeSeekBar.setSelectedMinValue(20);
        rangeSeekBar.setSelectedMaxValue(657);

        LinearLayout linear = new LinearLayout(this);

        linear.addView(rangeSeekBar);




        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                calorieMin = rangeSeekBar.getSelectedMinValue();
                calorieMax = rangeSeekBar.getSelectedMaxValue();


            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        calorieMin=0;
                        calorieMax=0;


                    }
                });
        builder.setView(linear);
        // 3. Get the AlertDialog from create()
        builder.show();



    }


    /*public void selectNutrientRange(View view){


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.nutrient_title)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });

        builder.setMultiChoiceItems(R.array.nutrient_options, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }

            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item

                temp = which;
            }
        });


        // Setup the new range seek bar
        RangeSeekBar<Integer> rangeSeekBar = new RangeSeekBar<Integer>(this);
        // Set the range
        rangeSeekBar.setRangeValues(0, 1000);
        rangeSeekBar.setSelectedMinValue(20);
        rangeSeekBar.setSelectedMaxValue(657);

        LinearLayout linear = new LinearLayout(this);

        linear.addView(rangeSeekBar);


        builder.setView(linear);
        // 3. Get the AlertDialog from create()
        builder.show();



    }*/



    public String generateURL(String keyword, int ingredients, String[] diet, String[] health, int calorieMin, int calorieMax){
            String url="https://api.edamam.com/search?app_id=d2788de0&app_key=053cc5a069567069a64745e46e5e8546&from=0&to=100";
            String keywordParam="";
            String ingredientParam="";
            String dietParam="";
            String healthParam="";
            String calorieParam="";

            keywordParam = "&q="+keyword;
            if (ingredients!=0){
                ingredientParam = "&ingr="+ingredients;


            }
            for(int i=0; i<diet.length; i++){
                if(diet[i]!=null){
                    dietParam += "&diet="+diet[i].toLowerCase();
                }
            }

        if(calorieMin!=0&&calorieMax!=0){
            if(calorieMin>0) {
                calorieParam += "&calories=gte"+calorieMin;

            }
            if(calorieMax>0){
                calorieParam += ",lte"+calorieMax;
            }
        }

            for(int i=0; i<health.length; i++){
                if(health[i]!=null){
                    healthParam += "&health="+health[i].toLowerCase();
                }
            }



            url=url+keywordParam+ingredientParam+calorieParam+dietParam+healthParam;





        return url;
    }







}
