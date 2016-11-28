package com.mokinjazz.and.martian.data.remote.rest;

import android.util.Log;

import com.mokinjazz.and.martian.data.remote.Query;
import com.mokinjazz.and.martian.data.remote.entity.CreditCollection;
import com.mokinjazz.and.martian.data.remote.entity.MovieCollection;
import com.mokinjazz.and.martian.data.remote.entity.MovieEntity;
import com.mokinjazz.and.martian.data.remote.service.MovieDBService;
import com.mokinjazz.and.martian.data.remote.service.SearchService;

import java.net.HttpURLConnection;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by vvasilyev on 11/5/15.
 */
@Singleton
public class RestApi extends RetrofitRestApi{

    MovieDBService dbService = builder.baseUrl(MOVIE_BASE_URL).build().create(MovieDBService.class);

    SearchService searchService = builder.baseUrl(SEARCH_BASE_URL).build().create(SearchService.class);

    @Inject
    public RestApi() {
    }

    private <T> T makeCall(Call<T> call) throws RestException {
        try {
            Response<T> r = call.execute();
            if (r.code() == HttpURLConnection.HTTP_OK) {
                return r.body();
            } else {
                String message = r.code() + " " + r.raw().request().urlString();
                Log.d(RestApi.class.getSimpleName(), message);
                throw new RestException(message);
            }
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    public MovieCollection getCollection(Query query) throws RestException {
        if (query != null) {
            return makeCall(searchService.movieList(API_KEY, query.value));
        }
        return makeCall(dbService.movieList(API_KEY));
    }

    public MovieEntity getMovie(long id) throws RestException {
        return makeCall(dbService.movie(id, API_KEY));
    }


}
