package com.pantos27.www.youtubesearcher;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Veierovioum on 06/03/2016.
 */
public class Converter implements retrofit2.Converter<JSONArray, String[]> {

    private static final String TAG = "searcher-";

    @Override
    public String[] convert(JSONArray value) throws IOException {
        String[] results;
        Log.d(TAG, "Json-convert: "+value.toString());
        try {
            JSONArray arr=value.getJSONArray(1);
            arr=arr.getJSONArray(0);
            results=new String[arr.length()];

            for (int i = 0; i < arr.length(); i++) {
                results[i]=arr.getString(i);
            }

        } catch (JSONException e) {
            Log.e(TAG, "convert: error "+e.getMessage(),e);
            e.printStackTrace();
            results =new String[0];
        }
        return results;
    }




}
