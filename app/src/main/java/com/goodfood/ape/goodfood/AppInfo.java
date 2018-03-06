package com.goodfood.ape.goodfood;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AppInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

    }

    //each of the following methods corresponds to the onClick methods for the TextViews. These generate a dialog with some informative text

    public void dialogGeneral(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("General");
        builder.setMessage("Welcome to the GoodFood app!\n" +
                "This app in an honours student’s dissertation project in collaboration with The Green & Blue Space.\n" +
                "The aim of the app is to promote and encourage healthier eating amongst university students and possibly staff.\n" +
                "By interacting with the app users can earn various achievements regarding their daily fruit and veg intake, cooking enthusiasm etc. More on this in other info sections!\n" +
                "We hope you find this app useful and interesting. Currently it has undergone its first stage of development, therefore has only a limited amount of functions, but this will hopefully be improved in the future.\n" +
                "Happy eating!\n");
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
        builder.setMessage("The Green & Blue Space provides the opportunity to order veg bags that contain seasonal and locally sourced veg. You can now order them via this app in the “Order Veg Bag” section.\n" +
                "\n" +
                "Times\n" +
                "For each week orders are only accepted on Mondays and Tuesdays until 5pm. Out of these times you will not be able to make an order.\n" +
                "If you have successfully made an order, you can pick up your veg bag on Thursday from 11:00 until 4:30pm in The Green & Blue Space, which is located in the Atrium towards the bridge exit.\n" +
                "\n" +
                "Field explanations\n" +
                "When making an order, choose the number of bags you would like to order (normally this would be 1, but in case you are ordering for a friend or hosting a huge dinner party, other options are available). Please also indicate if you would like to order any extras* or if you have any allergies (e.g. beetroot). Finally, leave a comment if you wish and click Submit. This will automatically generate the email for you and you’ll just have to click send from your email app.\n" +
                "\n" +
                "*Extras - additionally you can order eggs (£1.75 for 6 eggs) and also extra vegetable (e.g. sweet potatoes, leeks etc.) \n" +
                "\n" +
                "Pricing\n" +
                "The cost of one vegetable bag is £6.50.\n" +
                "A box of 6 eggs costs £1.75.\n"+
                "Prices for other extras vary, as these come from wholesale and the price is known only when the food arrives.");
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
        builder.setMessage("Search\n" +
                "The app provides a search facility for you to search by keyword, number of ingredients, dietary requirements etc. Enter your parameters and click Search for the results to be displayed. Please be sensible with the search parameters, as, if you enter too many, there will be no results!\n\n" +
                "Favourites\n" +
                "When you find a recipe you like and want to save it for later, click the star icon on the top right corner. You can later view these by clicking the Favourites button.\n\n" +
                "Done\n" +
                "When you have successfully cooked something from a recipe, mark it ‘Done’ by clicking the box in the top left corner. You can view the recipes you’ve already made by clicking the Done button.\n");
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

    public void dialogProfile(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setCancelable(true);
        builder.setTitle("My Profile");
        builder.setMessage("Daily Intake\n" +
                "It is important to eat fruit and veg on a daily basis. This will help you keep track of this. Whenever you eat some fruit or veg during the day, click the + button on this page. The goal is 5 pieces of fruit and veg per day, but don’t hesitate to eat more!\n\n" +
                "Achievements\n" +
                "Currently, there are 3 ways you can earn achievements – by making vegetable bag orders, by making recipes and by continuously eating 5 pieces of fruit and veg every day. These will give you badges that will be displayed in the Achievements section.\n\n" +
                "My Details\n" +
                "This section displays your current stats within the app.\n");
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
        builder.setMessage("Local Food Scene\n" +
                "In the Local Food Scene section you can find out about various places that can help you eat healthier. Just click on the images and will take you to the appropriate webpage with all the info!\n");
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
        builder.setMessage("If you are using this app and something breaks, looks weird, doesn’t work or else if you have any suggestions, please send an email to ape00007@students.stir.ac.uk");
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
