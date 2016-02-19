package com.example.veierovioum.lesson19_customadapter;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Veierovioum on 18/02/2016.
 */
    public class CountryAdapter implements ExpandableListAdapter {

    private static final String TAG = "expandAdapter";
    Context context;
    CitiesFragment fragment;
    ArrayList<Country> countries;
    private static final int fragmentContainerID=000056;

    public CountryAdapter(Context context, ArrayList<Country> countries) {
        this.context=context;
        this.countries=countries;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return countries.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return countries.get(groupPosition).getCities().length;
    }

    @Override
    public Country getGroup(int groupPosition) {
        return countries.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return countries.get(groupPosition).getCities()[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        TextView textView;
        if (convertView == null) {
             textView= (TextView) LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
        }
        else
            textView=(TextView) convertView;

        textView.setText(countries.get(groupPosition).toString());

        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView==null || fragment==null){
            convertView=new FrameLayout(context);
            convertView.setId(fragmentContainerID);
            convertView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT));
            fragment=new CitiesFragment();
            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction()
                    .add(convertView.getId(),fragment,"citiesFrag").commit();

            Log.d(TAG, "getChildView: fragment added");
        }

        fragment.setCities(countries.get(groupPosition).getCities());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {



    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
