package com.goodfood.ape.goodfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipeMenu extends AppCompatActivity {

    private PrefManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        if(prefManager.getCode()==true) {
            setContentView(R.layout.activity_recipe_menu);

            Button btnDone = findViewById(R.id.doneButton);
            btnDone.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent = new Intent(RecipeMenu.this, Done.class);
                    startActivity(intent);
                }
            });

        }

        if(prefManager.getCode()==false){
            setContentView(R.layout.activity_recipe_menu_no);
        }



        Button btnSearch = findViewById(R.id.searchButton);
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(RecipeMenu.this, Recipes.class);
                startActivity(intent);
            }
        });
        Button btnFavourites = findViewById(R.id.favouritesButton);
        btnFavourites.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(RecipeMenu.this, Favourites.class);
                startActivity(intent);
            }
        });






    }
}
