package com.goodfood.ape.goodfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyDetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);


        TextView dailyIntake = findViewById(R.id.dailyIntakeValue);
        TextView daysStrike = findViewById(R.id.daysStrikeValue);
        TextView recipes = findViewById(R.id.recipesMadeValue);
        TextView badges = findViewById(R.id.badgesEarnedValue);
        TextView orders = findViewById(R.id.ordersMadeValue);


        MyDBHandler db = new MyDBHandler(MyDetails.this, null, null, 1);

        dailyIntake.setText(Integer.toString(db.getDailyIntake()));
        daysStrike.setText(Integer.toString(db.getDaysStrike()));
        recipes.setText(Integer.toString(db.getRecipeCount()));
        badges.setText(Integer.toString(db.getBadgeCount()));
        orders.setText(Integer.toString(db.getOrderCount()));



    }
}
