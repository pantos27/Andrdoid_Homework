package com.pantos27.www.youtubesearcher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Veierovioum on 04/03/2016.
 */
public final class SearchService {

    public static final String API_URL="http://suggestqueries.google.com/complete/search";

    public static class SearchResult{
        public final String query;
        public final String[] results;

        public SearchResult(String query, String[] results) {
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
}
