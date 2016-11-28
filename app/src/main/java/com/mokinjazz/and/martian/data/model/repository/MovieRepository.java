package com.mokinjazz.and.martian.data.model.repository;

import com.mokinjazz.and.martian.data.model.Movie;
import com.mokinjazz.and.martian.data.remote.Query;
import com.mokinjazz.and.martian.data.remote.store.RemoteObject;

import java.util.List;

/**
 * Created by vvasilyev on 11/3/15.
 */
public interface MovieRepository {

    void getMovies(Callback<List<Movie>> callback);

    void getMovieData(long id, Callback<Movie> callback);

    void findMovie(Query query, Callback<List<Movie>> callback);

    interface Callback<T> extends RemoteObject.Callback<T> {

    }
}
