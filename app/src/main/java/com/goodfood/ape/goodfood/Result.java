package com.goodfood.ape.goodfood;

import java.io.Serializable;
import java.lang.reflect.Array;

/**
 * Created by ape on 05/02/2018.
 */

public class Result implements Serializable {

    private String uri;
    private String title;
    private String imageUrl;
    private String[] ingredients;
    private String instructionUrl;




    public Result(String uri, String title, String imageUrl, String[] ingredients, String instructionUrl) {

        //this._id = id;
        this.uri = uri;
        this.title = title;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.instructionUrl = instructionUrl;

    }


    public String getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getInstructionUrl() {
        return instructionUrl;
    }


    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructionUrl(String instructionUrl) {
        this.instructionUrl = instructionUrl;
    }
}
