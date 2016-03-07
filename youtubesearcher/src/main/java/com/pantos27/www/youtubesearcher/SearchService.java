package com.pantos27.www.youtubesearcher;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Veierovioum on 04/03/2016.
 */
public final class SearchService {

    private static final String TAG = "searcher-searchService";
    public static final String API_URL="http://suggestqueries.google.com";
    public static final HttpUrl url=HttpUrl.parse(API_URL);



//    Converter factory= Converter.Factory();
    private Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(new GsonConverterFactory().requestBodyConverter()).build();
    private SuggestQueries suggestQueries=retrofit.create(SuggestQueries.class);

    public SearchService() {
        Log.d(TAG, "SearchService: constractor");
        retrofit.requestBodyConverter()
    }


    public static class SearchResult{
        public final String[] results;

        public SearchResult(String[] query, String[] results) {
            Log.d(TAG, "SearchResult: constractor");
//            this.query = query;
            this.results = results;
        }
    }

        public interface SuggestQueries{
        @GET ("/complete/search")
        Call<String[]> searchResults(
                @Query("q") String query,
                @Query("client") String client);
    }

    public interface Callback{
        String[] onSuccess(String[] result);
    }

    public Callback callback;

    public  String[] getResultsAsync(String query){
        Log.d(TAG, "getResultsAsync: ");
        AsyncTask<String,Void,String[]> worker=new AsyncTask<String, Void, String[]>() {
            @Override
            protected String[] doInBackground(String... params) {
                Log.d(TAG, "doInBackground: "+params.toString());
                return getResults(params[0]);
            }

            @Override
            protected void onPostExecute(String[] strings) {
                Log.d(TAG, "onPostExecute: ");
                if (callback != null) {
                    callback.onSuccess(strings);
                }
            }
        } ;

        return null;
    }

    @Nullable
    public String[] getResults(String query) {

        Log.d(TAG, "getResults: "+query);
        Call<String[]> call=suggestQueries.searchResults(query,"firefox");

        try {
            return call.execute().body();

        } catch (IOException e) {
            Log.e(TAG, "getResults: "+call.toString(), e);
            e.printStackTrace();
        }
        return null;
    }
}
