package com.pantos27.www.youtubesearcher;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Veierovioum on 05/03/2016.
 */
public class SearchArrayAdapter extends ArrayAdapter<String> {

    private static final String TAG = "searcher-adapter";
    static ArrayList<String> dataSet=new ArrayList<>();
    int countReq;
    SearchService searchService;

    public SearchArrayAdapter(Context context, int resource) {
        super(context, resource);
        Log.d(TAG, "SearchArrayAdapter: ");
        searchService=new SearchService();
    }

    @Override
    public int getCount() {
            return dataSet.size();
    }

    @Override
    public String getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint==null)return null;

                Log.d(TAG, "performFiltering: "+constraint);
                countReq++;
                FilterResults results=new FilterResults();
                results.count=countReq;

                results.values=searchService.getResults(constraint.toString());

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results==null || results.count!=countReq || results.values==null) return;

                String[] strings= (String[]) results.values;
                dataSet.clear();
                for (String string : strings) {
                    dataSet.add(string);
                }

                SearchArrayAdapter.this.notifyDataSetChanged();
            }
        };
    }
}
