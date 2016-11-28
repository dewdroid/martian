package com.mokinjazz.and.martian.data.remote.service;

import com.mokinjazz.and.martian.data.remote.entity.MovieCollection;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Vladimir on 1/16/2016.
 */
public interface SearchService {


    @GET("movie")
    Call<MovieCollection> movieList(@Query("api_key") String key, @Query("query") String query);
}
