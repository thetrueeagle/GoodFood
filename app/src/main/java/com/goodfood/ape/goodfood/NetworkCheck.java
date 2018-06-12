package com.goodfood.ape.goodfood;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ape on 24/05/2018.
 */

public class NetworkCheck extends AsyncTask<String, Void, Boolean> {

    private Context context;
    private Activity activity;

    NetworkCheck(Activity activity) {
        this.context = activity.getApplicationContext();
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(Boolean result) {
        // Do something with the result here
        if (result==false){
            Toast.makeText(context, "No internet connection. Some functions may be limited. Please connect to the internet!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected Boolean doInBackground(String... params) {

        try {
            HttpURLConnection urlc = (HttpURLConnection)
                    (new URL("http://clients3.google.com/generate_204")
                            .openConnection());
            urlc.setRequestProperty("User-Agent", "Android");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1500);
            urlc.connect();
            return (urlc.getResponseCode() == 204 &&
                    urlc.getContentLength() == 0);
        } catch (IOException e) {
            Log.w("connection", "Error checking internet connection", e);
            return false;


        }


    }
}
