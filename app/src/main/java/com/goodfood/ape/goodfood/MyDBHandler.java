package com.goodfood.ape.goodfood;

/**
 * Created by ape on 19/01/2018.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.SecureRandom;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID =  "_id";
    public static final String COLUMN_FIRSTNAME =  "firstName";
    public static final String COLUMN_LASTNAME =  "lastName";
    public static final String COLUMN_EMAIL =  "email";
    public static final String COLUMN_POINTS =  "points";
    public static final String COLUMN_DAYS_STRIKE =  "strike";
    public static final String COLUMN_DAILY_INTAKE =  "dailyIntake";
    public static final String COLUMN_RECIPE_COUNT = "recipeCount";
    public static final String COLUMN_PASSWORD =  "password";


    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRSTNAME + " TEXT, "+
                    COLUMN_LASTNAME + " TEXT, "+
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_POINTS + " INTEGER, " +
                    COLUMN_DAYS_STRIKE+ " INTEGER, " +
                    COLUMN_DAILY_INTAKE + " INTEGER, " +
                    COLUMN_RECIPE_COUNT + " INTEGER" + ");";


public MyDBHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

    super(context, DATABASE_NAME, factory, DATABASE_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_QUERY);

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, "Anna");
        values.put(COLUMN_LASTNAME, "Eglite");
        values.put(COLUMN_EMAIL, "anna@gmail.com");
        values.put(COLUMN_PASSWORD, "github");
        values.put(COLUMN_POINTS, 0);
        values.put(COLUMN_DAYS_STRIKE, 0);
        values.put(COLUMN_DAILY_INTAKE, 0);
        values.put(COLUMN_RECIPE_COUNT, 0);

        db.insert(TABLE_NAME, null, values);
        //db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
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
    db.insert(TABLE_NAME, null, values);
    db.close();

    }


    //Delete a user from the database

/*    public void deleteUser(String password) {

        SQLiteDatabase db = getWritableDatabase();

        Users have to enter password to delete their profile. Add query here to check password --> retrieve matching email ID and then delete that row

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=\"" + userId + "\";" );


    }*/





    public String getName(String username){
        String name = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT firstName FROM " + TABLE_NAME + " WHERE email=\"" + username + "\";";
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

        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_DAILY_INTAKE + "=" + intake + ";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();

    }

    public boolean checkPassword(String email, String password) {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT password FROM " + TABLE_NAME + " WHERE email = \"" + email + "\" AND password = \"" + password + "\";";
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


/*    public String encryptPassword (String password){ //SECURITY NOT ESSENTIAL FOR THIS PROJECT




        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = BCrypt.checkpw(password, generatedSecuredPasswordHash);
        System.out.println(matched);




    }*/





}
