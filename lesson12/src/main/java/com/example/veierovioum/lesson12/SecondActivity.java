package com.example.veierovioum.lesson12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    String[] cities;
    Date backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //get cities list from the creating intent
        if (cities==null){
            cities=getIntent().getStringArrayExtra(getString(R.string.KEY_CITIES));

        }

        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);

        ListView lView=(ListView)(findViewById(R.id.listViewCities));
        lView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        //check if first press was pressed
        if (backPressed==null){
            backPressed=new Date();
            return;
        }
        //get the time now
        Date now=new Date();
        //compare times
        if((now.getTime()-backPressed.getTime())<5001){
            super.onBackPressed();
        }
        else{
            //set new time
            backPressed=now;
        }


    }
}
