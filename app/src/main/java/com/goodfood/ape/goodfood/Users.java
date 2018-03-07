package com.goodfood.ape.goodfood;

/**
 * Created by ape on 19/01/2018.
 */

public class Users {

    private int _id;
    private String firstName;
    private String lastName;
    private String email;
    private int points;
    private int daysStrike;
    private int dailyIntake;
    private int recipeCount;

    public Users(String name, String surname, String email, int points, int daysStrike, int dailyIntake, int recipeCount) {

        //this._id = id;
        this.firstName = name;
        this.lastName = surname;
        this.email = email;
        this.points = points;
        this.daysStrike = daysStrike;
        this.dailyIntake = dailyIntake;
        this.recipeCount = recipeCount;
    }

    public void set_id ( int id) {
        this._id = id;
    }

    public int get_id () {
        return _id;
    }


    public void setFirstName ( String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setLastName ( String lastName) {
        this.lastName = lastName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setEmail ( String email) {
        this.email = email;
    }

    public String getEmail () {
        return email;
    }



    public int getDailyIntake() {
        return dailyIntake;
    }

    public int getPoints() {
        return points;
    }

    public int getDaysStrike() {
        return daysStrike;
    }

    public int getRecipeCount() {
        return recipeCount;
    }

    public void setDailyIntake(int dailyIntake) {
        this.dailyIntake = dailyIntake;
    }

    public void setDaysStrike(int daysStrike) {
        this.daysStrike = daysStrike;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRecipeCount(int recipeCount) {
        this.recipeCount = recipeCount;
    }
}
