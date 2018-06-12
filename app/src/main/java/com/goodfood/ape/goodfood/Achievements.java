package com.goodfood.ape.goodfood;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Achievements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        MyDBHandler db = new MyDBHandler(Achievements.this, null, null, 1); //create a new DB Handler for database queries
        int count =1; //each time this page is viewed, the badge count is updated in the database

        TableLayout table = findViewById(R.id.achievementsTable);

        TableRow tr1 = new TableRow(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int windowWidth = size.x;
        int windowHeight = size.y;

        //the first badge will always be displayed, because this is the 'New user' badge and everyone will have this

        final ImageView image1 = new ImageView(this);
        image1.setImageResource(R.drawable.hello);

        tr1.addView(image1);
        image1.getLayoutParams().height=windowHeight/2-200;
        image1.getLayoutParams().width=windowWidth/2;
        TextView description1 = new TextView(this);
        description1.setText("New user - hurraaay!");
        description1.setGravity(Gravity.CENTER);
        description1.setTextAppearance(this, R.style.textAchievements);
        tr1.addView(description1);
        description1.getLayoutParams().height=windowHeight/2-200;
        description1.getLayoutParams().width=windowWidth/2;

        table.addView(tr1, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));



        if(db.getOrderCount()>=1){

            count+=1;
            TableRow tr2 = new TableRow(this);

            final ImageView image2 = new ImageView(this);
            image2.setImageResource(R.drawable.shop1);

            tr2.addView(image2);
            image2.getLayoutParams().height=windowHeight/2-200;
            image2.getLayoutParams().width=windowWidth/2;
            TextView description2 = new TextView(this);
            description2.setText("One Veg Bag order!");
            description2.setGravity(Gravity.CENTER);
            description2.setTextAppearance(this, R.style.textAchievements);
            tr2.addView(description2);
            description2.getLayoutParams().height=windowHeight/2-200;
            description2.getLayoutParams().width=windowWidth/2;

            table.addView(tr2, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getOrderCount()>=10){

            count+=1;
            TableRow tr3 = new TableRow(this);

            final ImageView image3 = new ImageView(this);
            image3.setImageResource(R.drawable.shop10);

            tr3.addView(image3);
            image3.getLayoutParams().height=windowHeight/2-200;
            image3.getLayoutParams().width=windowWidth/2;
            TextView description3 = new TextView(this);
            description3.setText("Ten Veg Bag order!");
            description3.setGravity(Gravity.CENTER);
            description3.setTextAppearance(this, R.style.textAchievements);
            tr3.addView(description3);
            description3.getLayoutParams().height=windowHeight/2-200;
            description3.getLayoutParams().width=windowWidth/2;

            table.addView(tr3, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getOrderCount()>=50){

            count+=1;
            TableRow tr4 = new TableRow(this);

            final ImageView image4 = new ImageView(this);
            image4.setImageResource(R.drawable.shop50);

            tr4.addView(image4);
            image4.getLayoutParams().height=windowHeight/2-200;
            image4.getLayoutParams().width=windowWidth/2;
            TextView description4 = new TextView(this);
            description4.setText("Fifty Veg Bag order!");
            description4.setGravity(Gravity.CENTER);
            description4.setTextAppearance(this, R.style.textAchievements);
            tr4.addView(description4);
            description4.getLayoutParams().height=windowHeight/2-200;
            description4.getLayoutParams().width=windowWidth/2;

            table.addView(tr4, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getOrderCount()>=100){

            count+=1;
            TableRow tr5 = new TableRow(this);

            final ImageView image5 = new ImageView(this);
            image5.setImageResource(R.drawable.shop100);

            tr5.addView(image5);
            image5.getLayoutParams().height=windowHeight/2-200;
            image5.getLayoutParams().width=windowWidth/2;
            TextView description5 = new TextView(this);
            description5.setText("Hundred Veg Bag order!");
            description5.setGravity(Gravity.CENTER);
            description5.setTextAppearance(this, R.style.textAchievements);
            tr5.addView(description5);
            description5.getLayoutParams().height=windowHeight/2-200;
            description5.getLayoutParams().width=windowWidth/2;

            table.addView(tr5, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getOrderCount()>=200){

            count+=1;
            TableRow tr6 = new TableRow(this);

            final ImageView image6 = new ImageView(this);
            image6.setImageResource(R.drawable.shop200);

            tr6.addView(image6);
            image6.getLayoutParams().height=windowHeight/2-200;
            image6.getLayoutParams().width=windowWidth/2;
            TextView description6 = new TextView(this);
            description6.setText("Two hundred Veg Bag order!");
            description6.setGravity(Gravity.CENTER);
            description6.setTextAppearance(this, R.style.textAchievements);
            tr6.addView(description6);
            description6.getLayoutParams().height=windowHeight/2-200;
            description6.getLayoutParams().width=windowWidth/2;

            table.addView(tr6, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getOrderCount()>=500){

            count+=1;
            TableRow tr7 = new TableRow(this);

            final ImageView image7 = new ImageView(this);
            image7.setImageResource(R.drawable.shop500);

            tr7.addView(image7);
            image7.getLayoutParams().height=windowHeight/2-200;
            image7.getLayoutParams().width=windowWidth/2;
            TextView description7 = new TextView(this);
            description7.setText("Five hundred Veg Bag order!");
            description7.setGravity(Gravity.CENTER);
            description7.setTextAppearance(this, R.style.textAchievements);
            tr7.addView(description7);
            description7.getLayoutParams().height=windowHeight/2-200;
            description7.getLayoutParams().width=windowWidth/2;

            table.addView(tr7, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getRecipeCount()>=1){

            count+=1;
            TableRow tr8 = new TableRow(this);

            final ImageView image8 = new ImageView(this);
            image8.setImageResource(R.drawable.cookhat1);

            tr8.addView(image8);
            image8.getLayoutParams().height=windowHeight/2-200;
            image8.getLayoutParams().width=windowWidth/2;
            TextView description8 = new TextView(this);
            description8.setText("First recipe made!");
            description8.setGravity(Gravity.CENTER);
            description8.setTextAppearance(this, R.style.textAchievements);
            tr8.addView(description8);
            description8.getLayoutParams().height=windowHeight/2-200;
            description8.getLayoutParams().width=windowWidth/2;

            table.addView(tr8, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getRecipeCount()>=10){

            count+=1;
            TableRow tr9 = new TableRow(this);

            final ImageView image9 = new ImageView(this);
            image9.setImageResource(R.drawable.cookhat10);

            tr9.addView(image9);
            image9.getLayoutParams().height=windowHeight/2-200;
            image9.getLayoutParams().width=windowWidth/2;
            TextView description9 = new TextView(this);
            description9.setText("Ten recipes made!");
            description9.setGravity(Gravity.CENTER);
            description9.setTextAppearance(this, R.style.textAchievements);
            tr9.addView(description9);
            description9.getLayoutParams().height=windowHeight/2-200;
            description9.getLayoutParams().width=windowWidth/2;

            table.addView(tr9, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getRecipeCount()>=50){

            count+=1;
            TableRow tr10 = new TableRow(this);

            final ImageView image10 = new ImageView(this);
            image10.setImageResource(R.drawable.cookhat50);

            tr10.addView(image10);
            image10.getLayoutParams().height=windowHeight/2-200;
            image10.getLayoutParams().width=windowWidth/2;
            TextView description10 = new TextView(this);
            description10.setText("Fifty recipes made!");
            description10.setGravity(Gravity.CENTER);
            description10.setTextAppearance(this, R.style.textAchievements);
            tr10.addView(description10);
            description10.getLayoutParams().height=windowHeight/2-200;
            description10.getLayoutParams().width=windowWidth/2;

            table.addView(tr10, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getRecipeCount()>=100){

            count+=1;
            TableRow tr11 = new TableRow(this);

            final ImageView image11 = new ImageView(this);
            image11.setImageResource(R.drawable.cookhat100);

            tr11.addView(image11);
            image11.getLayoutParams().height=windowHeight/2-200;
            image11.getLayoutParams().width=windowWidth/2;
            TextView description11 = new TextView(this);
            description11.setText("Hundred recipes made!");
            description11.setGravity(Gravity.CENTER);
            description11.setTextAppearance(this, R.style.textAchievements);
            tr11.addView(description11);
            description11.getLayoutParams().height=windowHeight/2-200;
            description11.getLayoutParams().width=windowWidth/2;

            table.addView(tr11, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getRecipeCount()>=500){

            count+=1;
            TableRow tr12 = new TableRow(this);

            final ImageView image12 = new ImageView(this);
            image12.setImageResource(R.drawable.cookhat500);

            tr12.addView(image12);
            image12.getLayoutParams().height=windowHeight/2-200;
            image12.getLayoutParams().width=windowWidth/2;
            TextView description12 = new TextView(this);
            description12.setText("Five hundred recipes made!");
            description12.setGravity(Gravity.CENTER);
            description12.setTextAppearance(this, R.style.textAchievements);
            tr12.addView(description12);
            description12.getLayoutParams().height=windowHeight/2-200;
            description12.getLayoutParams().width=windowWidth/2;

            table.addView(tr12, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getRecipeCount()>=1000){

            count+=1;
            TableRow tr13 = new TableRow(this);

            final ImageView image13 = new ImageView(this);
            image13.setImageResource(R.drawable.cookhat1000);

            tr13.addView(image13);
            image13.getLayoutParams().height=windowHeight/2-200;
            image13.getLayoutParams().width=windowWidth/2;
            TextView description13 = new TextView(this);
            description13.setText("Thousand recipes made!!!");
            description13.setGravity(Gravity.CENTER);
            description13.setTextAppearance(this, R.style.textAchievements);
            tr13.addView(description13);
            description13.getLayoutParams().height=windowHeight/2-200;
            description13.getLayoutParams().width=windowWidth/2;

            table.addView(tr13, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getDaysStreak()>=1){

            count+=1;
            TableRow tr14 = new TableRow(this);

            final ImageView image14 = new ImageView(this);
            image14.setImageResource(R.drawable.apple1);

            tr14.addView(image14);
            image14.getLayoutParams().height=windowHeight/2-200;
            image14.getLayoutParams().width=windowWidth/2;
            TextView description14 = new TextView(this);
            description14.setText("Holding a one day strike!");
            description14.setGravity(Gravity.CENTER);
            description14.setTextAppearance(this, R.style.textAchievements);
            tr14.addView(description14);
            description14.getLayoutParams().height=windowHeight/2-200;
            description14.getLayoutParams().width=windowWidth/2;

            table.addView(tr14, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getDaysStreak()>=5){

            count+=1;
            TableRow tr15 = new TableRow(this);

            final ImageView image15 = new ImageView(this);
            image15.setImageResource(R.drawable.apple5);

            tr15.addView(image15);
            image15.getLayoutParams().height=windowHeight/2-200;
            image15.getLayoutParams().width=windowWidth/2;
            TextView description15 = new TextView(this);
            description15.setText("Holding a five day strike!");
            description15.setGravity(Gravity.CENTER);
            description15.setTextAppearance(this, R.style.textAchievements);
            tr15.addView(description15);
            description15.getLayoutParams().height=windowHeight/2-200;
            description15.getLayoutParams().width=windowWidth/2;

            table.addView(tr15, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getDaysStreak()>=10){

            count+=1;
            TableRow tr16 = new TableRow(this);

            final ImageView image16 = new ImageView(this);
            image16.setImageResource(R.drawable.apple10);

            tr16.addView(image16);
            image16.getLayoutParams().height=windowHeight/2-200;
            image16.getLayoutParams().width=windowWidth/2;
            TextView description16 = new TextView(this);
            description16.setText("Holding a ten day strike!");
            description16.setGravity(Gravity.CENTER);
            description16.setTextAppearance(this, R.style.textAchievements);
            tr16.addView(description16);
            description16.getLayoutParams().height=windowHeight/2-200;
            description16.getLayoutParams().width=windowWidth/2;

            table.addView(tr16, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getDaysStreak()>=20){

            count+=1;
            TableRow tr17 = new TableRow(this);

            final ImageView image17 = new ImageView(this);
            image17.setImageResource(R.drawable.apple20);

            tr17.addView(image17);
            image17.getLayoutParams().height=windowHeight/2-200;
            image17.getLayoutParams().width=windowWidth/2;
            TextView description17 = new TextView(this);
            description17.setText("Holding a twenty day strike!");
            description17.setGravity(Gravity.CENTER);
            description17.setTextAppearance(this, R.style.textAchievements);
            tr17.addView(description17);
            description17.getLayoutParams().height=windowHeight/2-200;
            description17.getLayoutParams().width=windowWidth/2;

            table.addView(tr17, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getDaysStreak()>=50){

            count+=1;
            TableRow tr18 = new TableRow(this);

            final ImageView image18 = new ImageView(this);
            image18.setImageResource(R.drawable.apple50);

            tr18.addView(image18);
            image18.getLayoutParams().height=windowHeight/2-200;
            image18.getLayoutParams().width=windowWidth/2;
            TextView description18 = new TextView(this);
            description18.setText("Holding a fifty day strike!");
            description18.setGravity(Gravity.CENTER);
            description18.setTextAppearance(this, R.style.textAchievements);
            tr18.addView(description18);
            description18.getLayoutParams().height=windowHeight/2-200;
            description18.getLayoutParams().width=windowWidth/2;

            table.addView(tr18, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if(db.getDaysStreak()>=100){

            count+=1;
            TableRow tr19 = new TableRow(this);

            final ImageView image19 = new ImageView(this);
            image19.setImageResource(R.drawable.apple100);

            tr19.addView(image19);
            image19.getLayoutParams().height=windowHeight/2-200;
            image19.getLayoutParams().width=windowWidth/2;
            TextView description19 = new TextView(this);
            description19.setText("Holding a hundred day strike!");
            description19.setGravity(Gravity.CENTER);
            description19.setTextAppearance(this, R.style.textAchievements);
            tr19.addView(description19);
            description19.getLayoutParams().height=windowHeight/2-200;
            description19.getLayoutParams().width=windowWidth/2;

            table.addView(tr19, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }

        if((db.getDaysStreak()>=100)&&(db.getOrderCount()>=500)&&(db.getRecipeCount()>=1000))
        {
            count+=1;
            TableRow tr20 = new TableRow(this);

            final ImageView image20 = new ImageView(this);
            image20.setImageResource(R.drawable.win);

            tr20.addView(image20);
            image20.getLayoutParams().height=windowHeight/2-200;
            image20.getLayoutParams().width=windowWidth/2;
            TextView description20 = new TextView(this);
            description20.setText("All badges obtained!!!");
            description20.setGravity(Gravity.CENTER);
            description20.setTextAppearance(this, R.style.textAchievements);
            tr20.addView(description20);
            description20.getLayoutParams().height=windowHeight/2-200;
            description20.getLayoutParams().width=windowWidth/2;

            table.addView(tr20, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }

        db.updateBadgeCount(count);








    }



    }


