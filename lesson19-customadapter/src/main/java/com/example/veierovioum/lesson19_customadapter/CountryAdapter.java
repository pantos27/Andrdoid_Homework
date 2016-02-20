package com.example.veierovioum.lesson19_customadapter;

import android.content.Context;
import android.database.DataSetObserver;
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
    public class CountryAdapter implements ExpandableListAdapter, AdapterView.OnItemClickListener {

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
        if (convertView==null){
                convertView=LayoutInflater.from(context).inflate(R.layout.cities_list,null);

            adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1);
            ViewHolder holder=new ViewHolder(adapter);
            ListView lv=(ListView) convertView.findViewById(R.id.citiesListView);

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(this);
            convertView.setTag(holder);
        }
        else
        {
            ViewHolder holder=(ViewHolder) convertView.getTag();
            adapter=holder.getAdapter();
            adapter.clear();
        }
        String[] cities=countries.get(groupPosition).getCities();

        for (String city : cities) {
            adapter.add(city);
        }
        adapter.notifyDataSetChanged();
        return convertView;

//        CitiesFragment fragment=new CitiesFragment();
//
//        if (convertView==null){
//            convertView=new FrameLayout(context);
//            convertView.setId(fragmentContainerID);
//            convertView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT));
//            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction()
//                    .add(convertView.getId(),fragment,"citiesFrag").commit();
//
//            Log.d(TAG, "getChildView: fragment added");
//        }
//
//        fragment.setCities(countries.get(groupPosition).getCities());
//        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(view.getContext(), ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
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
