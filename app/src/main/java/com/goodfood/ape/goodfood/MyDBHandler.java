package com.goodfood.ape.goodfood;

/**
 * Created by ape on 19/01/2018.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;




public class MyDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID =  "_id";
    public static final String COLUMN_FIRSTNAME =  "firstName";
    public static final String COLUMN_LASTNAME =  "lastName";
    public static final String COLUMN_EMAIL =  "email";
    public static final String COLUMN_PASSWORD =  "password";


    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRSTNAME + " TEXT, "+
                    COLUMN_LASTNAME + " TEXT, "+
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT " + ");";


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
        values.put(COLUMN_PASSWORD, "helloworld");
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //Add new row to database

    public void addUser(Users user){

    ContentValues values = new ContentValues();
    values.put(COLUMN_FIRSTNAME, user.get_firstName());
    values.put(COLUMN_LASTNAME, user.get_lastName());
    values.put(COLUMN_EMAIL, user.get_email());
    values.put(COLUMN_PASSWORD, user.get_password());
    SQLiteDatabase db = getWritableDatabase();
    db.insert(TABLE_NAME, null, values);
    db.close();

    }


    //Delete a email from the database

    public void deleteUser(String password) {

        SQLiteDatabase db = getWritableDatabase();

        //Users have to enter password to delete their profile. Add query here to check password --> retrieve matching email ID and then delete that row

        //db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=\"" + userId + "\";" );


    }


    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT firstName FROM " + TABLE_NAME + " WHERE 1";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("firstName")) != null){
                dbString += c.getString(c.getColumnIndex("firstName"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return dbString;
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





}
