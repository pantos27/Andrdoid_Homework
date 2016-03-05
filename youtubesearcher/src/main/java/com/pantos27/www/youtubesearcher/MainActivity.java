package com.pantos27.www.youtubesearcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "searcher-Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "onCreate: ");

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);


        SearchArrayAdapter myAdapter = new SearchArrayAdapter(this, android.R.layout.simple_list_item_1);


        textView.setAdapter(myAdapter);






    }
}
