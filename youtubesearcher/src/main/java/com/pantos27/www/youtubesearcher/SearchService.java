package com.pantos27.www.youtubesearcher;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Veierovioum on 04/03/2016.
 */
public final class SearchService {

    private static final String TAG = "searcher-searchService";
    private static SearchService ourInstance = new SearchService();
    public static final String API_URL="http://suggestqueries.google.com/complete/search";

    public static SearchService getInstance() {
        Log.d(TAG, "getInstance: ");
        if (ourInstance == null) {
            Log.e(TAG, "getInstance: no instance",new NullPointerException() );
        }
        return ourInstance;
    }

    private Retrofit retrofit=new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private SuggestQueries suggestQueries=retrofit.create(SuggestQueries.class);

    private SearchService() {
        Log.d(TAG, "SearchService: constractor");
    }


    public static class SearchResult{
        public final String query;
        public final String[] results;

        public SearchResult(String query, String[] results) {
            Log.d(TAG, "SearchResult: constractor");
            this.query = query;
            this.results = results;
        }
    }

    public interface SuggestQueries{
        @GET
        Call<List<SearchResult>> searchResults(
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
        Call<List<SearchResult>> call=suggestQueries.searchResults(query,"firefox");

        try {
            List<SearchResult> results=call.execute().body();
            return results.get(0).results;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
