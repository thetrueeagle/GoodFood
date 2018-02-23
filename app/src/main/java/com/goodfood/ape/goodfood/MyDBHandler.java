package com.goodfood.ape.goodfood;

/**
 * Created by ape on 19/01/2018.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.HashMap;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 3;

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
    public static final String COLUMN_PASSWORD =  "password";
    public static final String COLUMN_URL =  "url";
    public static final String COLUMN_TITLE =  "title";


    private static final String CREATE_USER_TABLE_QUERY =
            "CREATE TABLE " + USER_TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRSTNAME + " TEXT, "+
                    COLUMN_LASTNAME + " TEXT, "+
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_POINTS + " INTEGER, " +
                    COLUMN_DAYS_STRIKE+ " INTEGER, " +
                    COLUMN_DAILY_INTAKE + " INTEGER, " +
                    COLUMN_RECIPE_COUNT + " INTEGER" + ");";

    private static final String CREATE_FAVOURITE_TABLE_QUERY =
            "CREATE TABLE " + FAVOURITE_TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, "+
                    COLUMN_URL + " TEXT" + ");";

    private static final String CREATE_DONE_TABLE_QUERY =
            "CREATE TABLE " + DONE_TABLE_NAME + " (" +
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
    values.put(COLUMN_PASSWORD, user.get_password());
    values.put(COLUMN_POINTS, 0);
    values.put(COLUMN_DAYS_STRIKE, 0);
    values.put(COLUMN_DAILY_INTAKE, 0);
    values.put(COLUMN_RECIPE_COUNT, 0);
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


    //Delete a user from the database

/*    public void deleteUser(String password) {

        SQLiteDatabase db = getWritableDatabase();

        Users have to enter password to delete their profile. Add query here to check password --> retrieve matching email ID and then delete that row

        db.execSQL("DELETE FROM " + USER_TABLE_NAME + " WHERE " + COLUMN_ID + "=\"" + userId + "\";" );


    }*/


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




    public String getName(String username){
        String name = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT firstName FROM " + USER_TABLE_NAME + " WHERE email=\"" + username + "\";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("firstName")) != null){
                name += c.getString(c.getColumnIndex("firstName"));
                name += "\n";
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

    public boolean checkPassword(String email, String password) {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT password FROM " + USER_TABLE_NAME + " WHERE email = \"" + email + "\" AND password = \"" + password + "\";";
        String storedPass = "";
        boolean check = false;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            if (c.getString(c.getColumnIndex("password")) !=null){
                storedPass = c.getString(c.getColumnIndex("password"));

            }
            c.moveToNext();
        }
        if (storedPass.equals(password)){
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


/*    public String encryptPassword (String password){ //SECURITY NOT ESSENTIAL FOR THIS PROJECT




        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = BCrypt.checkpw(password, generatedSecuredPasswordHash);
        System.out.println(matched);




    }*/





}
