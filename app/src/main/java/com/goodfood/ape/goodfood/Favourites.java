package com.goodfood.ape.goodfood;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Favourites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);


        ListView favourites = findViewById(R.id.favouritesList);
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        ArrayList<HashMap<String, String>> favouritesList = db.getDatabase(0);  //get the list of 'favourite' recipes from database table

        ListAdapter adapter = new SimpleAdapter(
                Favourites.this, favouritesList,
                R.layout.list_item, new String[]{"title", "url"}, new int[]{R.id.title, R.id.url});  //populate list view with database data

        favourites.setAdapter(adapter);

        favourites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {   //in an item in the list is clicked, open recipe

                TextView textView = view.findViewById(R.id.url);
                String url = textView.getText().toString();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        });


    }
}
