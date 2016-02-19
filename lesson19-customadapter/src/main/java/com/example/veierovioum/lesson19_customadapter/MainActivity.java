package com.example.veierovioum.lesson19_customadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Country> countries;
    CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.countries = new ArrayList<>();
        setCountries(this.countries);

        adapter= new CountryAdapter(this,countries);

        ExpandableListView lView=(ExpandableListView)(findViewById(R.id.listViewCountries));
        lView.setAdapter(adapter);
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


    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        //sate countries list
//        outState.putSerializable(getString(R.string.KEY_COUNTRIES), countries);
//    }


}
