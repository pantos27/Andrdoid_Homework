package com.example.veierovioum.lesson19_customadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Veierovioum on 18/02/2016.
 */
public class CitiesFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String CITIES_KEY = "key cities";
    private static final String TAG = "expandAdapter";
    String[] cities=new String[0];
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view=inflater.inflate(R.layout.cities_fragmnt,null);


        if (cities != null) {
            ListView lv=(ListView) view.findViewById(R.id.citiesListView);
            adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,cities);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(this);
        }

        return view;
    }

    public String[] getCities() {
        return cities;
    }

    public void setCities(String[] cities) {
        Log.d(TAG, "setCities: ");
        this.cities = cities;

        if (adapter != null) {
            Log.d(TAG, "setCities: notefied");
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           Toast.makeText(view.getContext(),adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }
}
