package com.example.aattar.lesson12_exe3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Colour> coloursList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get colours list from saved state or create new
        if (coloursList==null)
        {
            if (savedInstanceState==null){
                coloursList=fillColoursList();
            }
            else{
                coloursList= (ArrayList<Colour>) savedInstanceState.get(getString(R.string.KEY_COLOURS));
            }
        }

        Spinner spinner= (Spinner) findViewById(R.id.spinner);

        final ArrayAdapter<Colour> adapter=new ArrayAdapter<Colour>(this,android.R.layout.simple_expandable_list_item_1,coloursList);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //parent.setBackgroundColor(adapter.getItem(position).getColour());
                //Spinner _spinner= (Spinner) parent;

                TextView tv= (TextView) view;
                tv.setTextColor(adapter.getItem(position).getColour());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


    }

    private ArrayList<Colour> fillColoursList(){
        ArrayList<Colour> list=new ArrayList<>();

        list.add(new Colour(16711680,"Red"));
        list.add(new Colour(65280,"Green"));
        list.add(new Colour(255,"Blue"));
        list.add(new Colour(0,"Black"));
        list.add(new Colour(16777215,"White"));
        list.add(new Colour(16776960,"Yellow"));

        return list;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(getString(R.string.KEY_COLOURS), coloursList);
    }
}
