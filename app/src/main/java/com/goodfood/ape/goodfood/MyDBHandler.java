package com.goodfood.ape.goodfood;

/**
 * Created by ape on 19/01/2018.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 9;

    public static final String USER_TABLE_NAME = "users";
    public static final String FAVOURITE_TABLE_NAME = "favourite";
    public static final String DONE_TABLE_NAME = "done";
    public static final String COLUMN_ID =  "_id";
    public static final String COLUMN_FIRSTNAME =  "firstName";
    public static final String COLUMN_LASTNAME =  "lastName";
    public static final String COLUMN_EMAIL =  "email";
    public static final String COLUMN_POINTS =  "points";
    public static final String COLUMN_DAYS_STRIKE =  "strike";
    public static final String COLUMN_DAILY_INTAKE =  "dailyIntake";
    public static final String COLUMN_RECIPE_COUNT = "recipeCount";
    public static final String COLUMN_ORDER_COUNT = "orderCount";
    public static final String COLUMN_BADGE_COUNT = "badgeCount";
    public static final String COLUMN_URL =  "url";
    public static final String COLUMN_TITLE =  "title";


    private static final String CREATE_USER_TABLE_QUERY =
            "CREATE TABLE " + USER_TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRSTNAME + " TEXT, "+
                    COLUMN_LASTNAME + " TEXT, "+
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_POINTS + " INTEGER, " +
                    COLUMN_DAYS_STRIKE+ " INTEGER, " +
                    COLUMN_DAILY_INTAKE + " INTEGER, " +
                    COLUMN_RECIPE_COUNT + " INTEGER," +
                    COLUMN_ORDER_COUNT + " INTEGER," +
                    COLUMN_BADGE_COUNT + " INTEGER" +");";

    private static final String CREATE_FAVOURITE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS " + FAVOURITE_TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, "+
                    COLUMN_URL + " TEXT" + ");";

    private static final String CREATE_DONE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS " + DONE_TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, "+
                    COLUMN_URL + " TEXT" + ");";


public MyDBHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

    super(context, DATABASE_NAME, factory, DATABASE_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE_QUERY);
        db.execSQL(CREATE_FAVOURITE_TABLE_QUERY);
        db.execSQL(CREATE_DONE_TABLE_QUERY);


        //db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FAVOURITE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DONE_TABLE_NAME);
        onCreate(db);

    }

    //Add new row to database

    public void addUser(Users user){

    ContentValues values = new ContentValues();
    values.put(COLUMN_FIRSTNAME, user.getFirstName());
    values.put(COLUMN_LASTNAME, user.getLastName());
    values.put(COLUMN_EMAIL, user.getEmail());
    values.put(COLUMN_POINTS, 0);
    values.put(COLUMN_DAYS_STRIKE, 0);
    values.put(COLUMN_DAILY_INTAKE, 0);
    values.put(COLUMN_RECIPE_COUNT, 0);
    values.put(COLUMN_ORDER_COUNT, 0);
    values.put(COLUMN_BADGE_COUNT, 0);
    SQLiteDatabase db = getWritableDatabase();
    db.insert(USER_TABLE_NAME, null, values);
    db.close();

    }

    public void addRecipe(Result recipe, int which){



        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, recipe.getTitle());
        values.put(COLUMN_URL, recipe.getInstructionUrl());
        SQLiteDatabase db = getWritableDatabase();
        if(which==0) {
            db.insert(FAVOURITE_TABLE_NAME, null, values);
        }
        else if(which==1){
            db.insert(DONE_TABLE_NAME, null, values);
        }
        db.close();

    }

    public void deleteRecipe(Result recipe, int which){

        SQLiteDatabase db = getWritableDatabase();

        if(which==0) {
            db.execSQL("DELETE FROM " + FAVOURITE_TABLE_NAME + " WHERE " + COLUMN_URL + "=\"" + recipe.getInstructionUrl() + "\";");
        }
        else if(which==1){
            db.execSQL("DELETE FROM " + DONE_TABLE_NAME + " WHERE " + COLUMN_URL + "=\"" + recipe.getInstructionUrl() + "\";");
        }
    }


    public boolean checkRecipe(Result recipe, int which) {

        SQLiteDatabase db = getWritableDatabase();
        String query="";
        if(which==0) {
            query = "SELECT count(*) FROM " + FAVOURITE_TABLE_NAME + " WHERE url = \"" + recipe.getInstructionUrl() + "\";";
        }
        else if(which==1){
            query = "SELECT count(*) FROM " + DONE_TABLE_NAME + " WHERE url = \"" + recipe.getInstructionUrl() + "\";";
        }
        boolean check = false;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int count = c.getInt(0);
        if (count>0){

            check = true;
        }
        else if (count==0){
            check = false;
        }

        db.close();
        c.close();

        return check;
    }




    public ArrayList<HashMap<String, String>> getDatabase(int which){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query="";
        if(which==0) {
            query = "SELECT title, url FROM " + FAVOURITE_TABLE_NAME + ";";
        }
        else if(which==1){
            query = "SELECT title, url FROM " + DONE_TABLE_NAME + ";";
        }
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("title")) != null){
                HashMap<String, String> recipe = new HashMap<>();
                recipe.put("title", c.getString(c.getColumnIndex("title")));
                recipe.put("url", c.getString(c.getColumnIndex("url")));
                list.add(recipe);
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return list;
    }




    public String getName(){
        String name = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT firstName FROM " + USER_TABLE_NAME + ";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("firstName")) != null){
                name += c.getString(c.getColumnIndex("firstName"));
                //name += "\n";
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return name;
    }


    public String getSurname(){
        String name = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT lastName FROM " + USER_TABLE_NAME + ";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("lastName")) != null){
                name += c.getString(c.getColumnIndex("lastName"));
                //name += "\n";
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return name;
    }

    public void updateDailyIntake(int intake){

        String query = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_DAILY_INTAKE + "=" + intake + ";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();

    }

    public void updateBadgeCount(int badges){

        String query = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_BADGE_COUNT + "=" + badges + ";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();

    }

    public int getDailyIntake(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_DAILY_INTAKE + " FROM " + USER_TABLE_NAME + ";";
        int intake = 0;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            if (c.getString(c.getColumnIndex(COLUMN_DAILY_INTAKE)) !=null){
                intake = c.getInt(c.getColumnIndex(COLUMN_DAILY_INTAKE));

            }
            c.moveToNext();
        }

        return intake;

    }

    public int getBadgeCount(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_BADGE_COUNT + " FROM " + USER_TABLE_NAME + ";";
        int badges = 0;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            if (c.getString(c.getColumnIndex(COLUMN_BADGE_COUNT)) !=null){
                badges = c.getInt(c.getColumnIndex(COLUMN_BADGE_COUNT));

            }
            c.moveToNext();
        }

        return badges;

    }

    public int getDaysStrike(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_DAYS_STRIKE + " FROM " + USER_TABLE_NAME + ";";
        int strike = 0;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            if (c.getString(c.getColumnIndex(COLUMN_DAYS_STRIKE)) !=null){
                strike = c.getInt(c.getColumnIndex(COLUMN_DAYS_STRIKE));

            }
            c.moveToNext();
        }

        return strike;

    }


    public int getRecipeCount(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_RECIPE_COUNT + " FROM " + USER_TABLE_NAME + ";";
        int recipes = 0;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            if (c.getString(c.getColumnIndex(COLUMN_RECIPE_COUNT)) !=null){
                recipes = c.getInt(c.getColumnIndex(COLUMN_RECIPE_COUNT));

            }
            c.moveToNext();
        }

        return recipes;

    }

    public int getOrderCount(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_ORDER_COUNT + " FROM " + USER_TABLE_NAME + ";";
        int orders = 0;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            if (c.getString(c.getColumnIndex(COLUMN_ORDER_COUNT)) !=null){
                orders = c.getInt(c.getColumnIndex(COLUMN_ORDER_COUNT));

            }
            c.moveToNext();
        }

        return orders;

    }


    public void updateRecipeCount(int which){

        SQLiteDatabase db = getWritableDatabase();
        String query="";
        String update="";
        if(which==0) {
            //for future if necessary to use the count of favourite recipes; currently not needed
            //query = "SELECT count(*) FROM " + FAVOURITE_TABLE_NAME + ";";

        }
        else if(which==1){
            query = "SELECT count(*) FROM " + DONE_TABLE_NAME + ";";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            int count = c.getInt(0);
            update = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_RECIPE_COUNT + "=" + count + ";";
        }


        db.execSQL(update);
        db.close();

    }


    public void updateOrderCount(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_ORDER_COUNT + " FROM " + USER_TABLE_NAME + ";";
        int orders = 0;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            if (c.getString(c.getColumnIndex(COLUMN_ORDER_COUNT)) !=null){
                orders = c.getInt(c.getColumnIndex(COLUMN_ORDER_COUNT));

            }
            c.moveToNext();
        }

        orders=orders+1;



            String update = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_ORDER_COUNT + "=" + orders + ";";



        db.execSQL(update);
        db.close();

    }



    public void checkDateAndUpdate(boolean isStrike){

        SQLiteDatabase db = getWritableDatabase();
            //update the database
            if(isStrike) {


                String query = "SELECT " + COLUMN_DAILY_INTAKE + " FROM " + USER_TABLE_NAME + ";";
                int intake = 0;
                Cursor c = db.rawQuery(query, null);
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    if (c.getString(c.getColumnIndex(COLUMN_DAILY_INTAKE)) != null) {
                        intake = c.getInt(c.getColumnIndex(COLUMN_DAILY_INTAKE));

                    }
                    c.moveToNext();
                }

                if (intake >= 5) {

                    query = "SELECT " + COLUMN_DAYS_STRIKE + " FROM " + USER_TABLE_NAME + ";";
                    int strike = 0;
                    c = db.rawQuery(query, null);
                    c.moveToFirst();
                    while (!c.isAfterLast()) {

                        if (c.getString(c.getColumnIndex(COLUMN_DAYS_STRIKE)) != null) {
                            strike = c.getInt(c.getColumnIndex(COLUMN_DAYS_STRIKE));

                        }
                        c.moveToNext();
                    }
                    strike = strike + 1;

                    query = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_DAYS_STRIKE + "=" + strike + ";";
                    db.execSQL(query);
                    query = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_DAILY_INTAKE + "=0;";
                    db.execSQL(query);

                }
            }
            else if(isStrike==false){
                String query = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_DAYS_STRIKE + "=0;";
                db.execSQL(query);
                query = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_DAILY_INTAKE + "=0;";
                db.execSQL(query);

            }





    }




    public boolean checkData(String email) {



        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT email FROM " + USER_TABLE_NAME + " WHERE email = \"" + email + "\";";
        String storedEmail = "";

        boolean check = false;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            if (c.getString(c.getColumnIndex("email")) !=null){
                storedEmail = c.getString(c.getColumnIndex("email"));

            }
            c.moveToNext();
        }



        if (storedEmail.equals(email)){
            check = true;
        }

        db.close();
        c.close();

        return check;
    }


    public boolean checkDatabaseEmpty() {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT count(*) FROM " + USER_TABLE_NAME +  ";";
        boolean check = false;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int count = c.getInt(0);
        if (count>0){

            check = false;
        }
        else if (count==0){
            check = true;
        }

        db.close();
        c.close();

        return check;
    }






}
