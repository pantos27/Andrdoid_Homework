package com.example.veierovioum.lesson19_customadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnGroupExpandListener {
    private static final String TAG = "expandAdapter";
    ArrayList<Country> countries;
    CountryAdapter adapter;
    int expandedGroup=-1;
    ExpandableListView lView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.countries = new ArrayList<>();
        setCountries(this.countries);

        adapter= new CountryAdapter(this,countries);

        lView=(ExpandableListView)(findViewById(R.id.listViewCountries));
        lView.setAdapter(adapter);

        lView.setOnGroupExpandListener(this);

        //set the listener for an item on the list click
//        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
//
//                intent.putExtra(getString(R.string.KEY_CITIES),adapter.getItem(position).getCities());
//
//                startActivity(intent);
//            }
//        });


    }

    public void setCountries(ArrayList<Country> countries) {

        countries.add(new Country("Angola", "Luanda", "Lobito", "Huambo"));
        countries.add(new Country("Spain","Madrid", "Gijon", "Bilbao"));
        countries.add(new Country("Mexico", "Mexico City", "Tijuana", "Guadalajara"));
        countries.add(new Country("Australia", "Brisbane", "Sydney", "Perth"));
        countries.add(new Country("Sri Lanka", "Colombo", "Batticaloa", "Matara"));
        countries.add(new Country("Israel","Haifa","Ashdod","Yokneam"));
        countries.add(new Country("Angola2", "Luanda", "Lobito", "Huambo"));
        countries.add(new Country("Spain2","Madrid", "Gijon", "Bilbao"));
        countries.add(new Country("Mexico2", "Mexico City", "Tijuana", "Guadalajara"));
        countries.add(new Country("Australia2", "Brisbane", "Sydney", "Perth"));
        countries.add(new Country("Sri Lanka2", "Colombo", "Batticaloa", "Matara"));
        countries.add(new Country("Israel2","Haifa","Ashdod","Yokneam"));
        countries.add(new Country("Angola3", "Luanda", "Lobito", "Huambo"));
        countries.add(new Country("Spain3","Madrid", "Gijon", "Bilbao"));
        countries.add(new Country("Mexico3", "Mexico City", "Tijuana", "Guadalajara"));
        countries.add(new Country("Australia3", "Brisbane", "Sydney", "Perth"));
        countries.add(new Country("Sri Lanka3", "Colombo", "Batticaloa", "Matara"));
        countries.add(new Country("Israel3","Haifa","Ashdod","Yokneam"));



    }

    @Override
    public void onGroupExpand(int groupPosition) {
        Log.d(TAG, "onGroupExpand: ");
        if (expandedGroup>-1)
            lView.collapseGroup(expandedGroup);

        expandedGroup=groupPosition;
    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        //sate countries list
//        outState.putSerializable(getString(R.string.KEY_COUNTRIES), countries);
//    }


}
