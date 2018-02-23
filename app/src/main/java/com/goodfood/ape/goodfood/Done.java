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

public class Done extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);


        ListView done = findViewById(R.id.doneList);
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        ArrayList<HashMap<String, String>> doneList = db.getDatabase(1);

        ListAdapter adapter = new SimpleAdapter(
                Done.this, doneList,
                R.layout.list_item, new String[]{"title", "url"}, new int[]{R.id.title, R.id.url});

        done.setAdapter(adapter);


        done.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView textView = view.findViewById(R.id.url);
                String url = textView.getText().toString();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        });




    }
}
