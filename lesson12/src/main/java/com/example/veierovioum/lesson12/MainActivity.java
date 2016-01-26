package com.example.veierovioum.lesson12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Country> countries;
    ArrayAdapter<Country> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check if countries list exists
        if (countries==null){
            if (savedInstanceState!=null){
                //get countries list from saved state
                this.countries= (ArrayList<Country>) savedInstanceState.get(getString(R.string.KEY_COUNTRIES));
            }
            else{
                //create and fill countires list
                this.countries = new ArrayList<>();
                setCountries(this.countries);
            }
        }

        adapter=new ArrayAdapter<Country>(this,android.R.layout.simple_list_item_1,countries);

        ListView lView=(ListView)(findViewById(R.id.listViewCountries));
        lView.setAdapter(adapter);
        //set the listener for an item on the list click
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);

                intent.putExtra(getString(R.string.KEY_CITIES),adapter.getItem(position).getCities());

                startActivity(intent);
            }
        });

    }

    public void setCountries(ArrayList<Country> countries) {

        countries.add(new Country("Angola", new String[]{"Luanda", "Lobito", "Huambo"}));
        countries.add(new Country("Spain", new String[]{"Madrid", "Gijon", "Bilbao"}));
        countries.add(new Country("Mexico", new String[]{"Mexico City", "Tijuana", "Guadalajara"}));
        countries.add(new Country("Australia", new String[]{"Brisbane", "Sydney", "Perth"}));
        countries.add(new Country("Sri Lanka", new String[]{"Colombo", "Batticaloa", "Matara"}));



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //sate countries list
        outState.putSerializable(getString(R.string.KEY_COUNTRIES),countries);
    }

    @Override
    public void onBackPressed() {

        if (countries.size()>0)
        {
            //remove last item on list
            countries.remove(countries.size()-1);
            adapter.notifyDataSetChanged();
        }
        else {
            //close
            super.onBackPressed();
        }
    }
}
