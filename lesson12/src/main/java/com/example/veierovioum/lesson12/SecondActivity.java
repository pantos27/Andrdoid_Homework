package com.example.veierovioum.lesson12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondActivity extends AppCompatActivity {

    String[] cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (cities==null){
            cities=getIntent().getStringArrayExtra(getString(R.string.KEY_CITIES));

        }

        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);

        ListView lView=(ListView)(findViewById(R.id.listViewCities));
        lView.setAdapter(adapter);
    }
}
