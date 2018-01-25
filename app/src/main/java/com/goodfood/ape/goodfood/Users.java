package com.goodfood.ape.goodfood;

/**
 * Created by ape on 19/01/2018.
 */

public class Users {

    private int _id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Users(String name, String surname, String email, String password) {

        //this._id = id;
        this.firstName = name;
        this.lastName = surname;
        this.email = email;
        this.password = password;
    }

    public void set_id ( int id) {
        this._id = id;
    }

    public int get_id () {
        return _id;
    }

    public void set_firstName ( String firstName) {
        this.firstName = firstName;
    }

    public String get_firstName () {
        return firstName;
    }

    public void set_lastName ( String lastName) {
        this.lastName = lastName;
    }

    public String get_lastName () {
        return lastName;
    }

    public void set_email ( String email) {
        this.email = email;
    }

    public String get_email () {
        return email;
    }

    public void set_password ( String password) {
        this.password = password;
    }

    public String get_password () {
        return password;
    }

}
