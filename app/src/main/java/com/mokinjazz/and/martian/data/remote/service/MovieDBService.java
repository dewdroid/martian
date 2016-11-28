package com.mokinjazz.and.martian.data.remote.service;

import com.mokinjazz.and.martian.data.remote.entity.CreditCollection;
import com.mokinjazz.and.martian.data.remote.entity.MovieCollection;
import com.mokinjazz.and.martian.data.remote.entity.MovieEntity;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by vvasilyev on 11/5/15.
 */
public interface MovieDBService {

    @GET("now_playing")
    Call<MovieCollection> movieList(@Query("api_key") String key);


    @GET("{id}?append_to_response=credits")
    Call<MovieEntity> movie(@Path("id") long id, @Query("api_key") String key);

    @GET("{id}/credits")
    Call<CreditCollection> credits(@Path("id") long id, @Query("api_key") String key);
}
