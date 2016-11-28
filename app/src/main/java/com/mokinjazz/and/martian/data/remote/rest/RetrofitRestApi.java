package com.mokinjazz.and.martian.data.remote.rest;

import com.google.gson.GsonBuilder;
import com.mokinjazz.and.martian.data.remote.service.MovieDBService;
import com.mokinjazz.and.martian.data.remote.service.SearchService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by vvasilyev on 2/23/16.
 */
public class RetrofitRestApi {

    protected static final String BASE_URL = "https://api.themoviedb.org/3";

    protected static final String API_KEY = "35ef6b52be203a09e5b5555aad3bab61";
    protected static final String MOVIE_BASE_URL = BASE_URL + "/movie/";
    protected static final String SEARCH_BASE_URL = BASE_URL + "/search/";

    protected Retrofit.Builder builder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder().create()));

    public static class RestException extends Exception{

        public RestException(Exception e) {
            super(e);
        }

        public RestException(String message) {
            super(message);
        }
    }
}
