package com.pantos27.www.youtubesearcher;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

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
//    private static SearchService ourInstance = new SearchService();
    public static final String API_URL="http://suggestqueries.google.com";
    public static final HttpUrl url=HttpUrl.parse(API_URL);

//    public static SearchService getInstance() {
//        Log.d(TAG, "getInstance: ");
//        if (ourInstance == null) {
//            Log.e(TAG, "getInstance: no instance",new NullPointerException() );
//        }
//        return ourInstance;
//    }

    private Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(SimpleXmlConverterFactory.create()).build();
    private SuggestQueries suggestQueries=retrofit.create(SuggestQueries.class);

    public SearchService() {
        Log.d(TAG, "SearchService: constractor");

    }


    public static class SearchResult{
        public final String[] query;
        public final String[] results;

        public SearchResult(String[] query, String[] results) {
            Log.d(TAG, "SearchResult: constractor");
            this.query = query;
            this.results = results;
        }
    }
    @Root
    public class Toplevel {
        @Path("CompleteSuggestion/suggestion")
        @Element
        public String data;
//
//        public class CompleteSuggestion {
//            public Suggestion suggestion;
//
//            public class Suggestion {
//                public String data;
//            }
//        }
    }

        public interface SuggestQueries{
        @GET ("/complete/search")
        Call<List<Toplevel>> searchResults(
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
        Call<List<Toplevel>> call=suggestQueries.searchResults(query,"firefox");

        try {
            List<Toplevel> results=call.execute().body();
            ArrayList<String> stringResults=new ArrayList<>();

            for (Toplevel result : results) {

                    stringResults.add(result.data);

            }
            String[] toReturn=new String[stringResults.size()];
            return stringResults.toArray(toReturn);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
