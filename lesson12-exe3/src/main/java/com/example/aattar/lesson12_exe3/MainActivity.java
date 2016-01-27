package com.example.aattar.lesson12_exe3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
                //create new list
                coloursList=fillColoursList();
            }
            else{
                //gets saved list from bundle
                coloursList= (ArrayList<Colour>) savedInstanceState.get(getString(R.string.KEY_COLOURS));
            }
        }
        //set spinner with the data
        Spinner spinner= (Spinner) findViewById(R.id.spinner);

        final ArrayAdapter<Colour> adapter=new ArrayAdapter<Colour>(this,android.R.layout.simple_expandable_list_item_1,coloursList);

        spinner.setAdapter(adapter);

        //handle item selected event
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {
                    //check if null, because shit happens
                    if (view!=null)
                    {
                        TextView tv=(TextView)view;
                        tv.setTextColor(adapter.getItem(position).getColour());
                    }

                }
                catch (Exception e){
                    Log.e("12-3",e.getMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing

            }
        });


    }

    /**
     * fills the array with wonderful colours
     * @return a new instance of the filled array
     */
    private ArrayList<Colour> fillColoursList(){
        ArrayList<Colour> list=new ArrayList<>();

        list.add(new Colour(Color.BLACK,"Black"));
        list.add(new Colour(Color.RED,"Red"));
        list.add(new Colour(Color.GREEN,"Green"));
        list.add(new Colour(Color.BLUE,"Blue"));
        list.add(new Colour(Color.WHITE,"White"));
        list.add(new Colour(Color.YELLOW,"Yellow"));
        list.add(new Colour(Color.CYAN,"Cyan"));
        list.add(new Colour(Color.GRAY,"Gray"));
        list.add(new Colour(Color.MAGENTA,"Magenta"));

        return list;
    }

    /**
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(getString(R.string.KEY_COLOURS), coloursList);
    }
}
