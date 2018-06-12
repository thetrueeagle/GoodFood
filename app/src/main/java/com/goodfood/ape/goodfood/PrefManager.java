package com.goodfood.ape.goodfood;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ape on 08/05/2018.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREFS = "MyPrefs";
    private static final String PREF_NAME = "name";
    private static final String PREF_CODE = "code";
    private static final String PREF_DATE = "date";
    private static final String PREF_DATACOLL = "data";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFS, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setName(String name){
        editor.putString(PREF_NAME, name);
        editor.commit();
    }

    public String getName(){
        return pref.getString(PREF_NAME, "");
    }

    public void removePref(String pref){
        editor.remove(pref);
        editor.apply();
    }

    public void setCode(Boolean version){
        editor.putBoolean(PREF_CODE, version);
        editor.commit();
    }

    public Boolean getCode(){
        return pref.getBoolean(PREF_CODE, false);
    }

    public void setDate(int date){
        editor.putInt(PREF_DATE, date);
        editor.commit();
    }

    public int getDate(){
        return pref.getInt(PREF_DATE, -1);
    }

    public void setDataColl(Boolean version){
        editor.putBoolean(PREF_DATACOLL, version);
        editor.commit();
    }

    public Boolean getDataColl(){
        return pref.getBoolean(PREF_DATACOLL, true);
    }

}
