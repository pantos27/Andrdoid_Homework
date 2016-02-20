package com.example.veierovioum.lesson19_customadapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Veierovioum on 18/02/2016.
 */
    public class CountryAdapter implements ExpandableListAdapter, AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "expandAdapter";
    private static final int KEY_HOLDER = 3084;
    Context context;
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

        ArrayAdapter<String> adapter;
        TextView tv;
        if (convertView==null){
            tv= (TextView) LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,null);

            tv.setPadding(context.getResources().getDimensionPixelSize(R.dimen.city_item_padding),0,0,0);
            tv.setPaddingRelative(context.getResources().getDimensionPixelSize(R.dimen.city_item_padding),0,0,0);
            tv.setBackgroundColor(Color.LTGRAY);
            tv.setOnClickListener(this);
        }
        else
        {
            tv= (TextView) convertView;
        }

        tv.setText(countries.get(groupPosition).getCities()[childPosition]);
        return tv;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), ((TextView)v).getText(), Toast.LENGTH_SHORT).show();

    }

    class ViewHolder {
        ArrayAdapter<String> adapter;

        public ViewHolder(ArrayAdapter<String> adapter) {
            this.adapter = adapter;
        }

        public ArrayAdapter<String> getAdapter() {
            return adapter;
        }
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
