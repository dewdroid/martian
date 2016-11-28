package com.mokinjazz.and.martian.data.model.repository.impl;

import com.mokinjazz.and.martian.data.model.Movie;
import com.mokinjazz.and.martian.data.model.repository.MovieRepository;
import com.mokinjazz.and.martian.data.remote.store.MovieRemoteStore;
import com.mokinjazz.and.martian.data.remote.Query;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by vvasilyev on 11/4/15.
 */
@Singleton
public class MovieRepositoryImpl implements MovieRepository {

    private MovieRemoteStore remoteStore;

    @Inject
    public MovieRepositoryImpl(MovieRemoteStore remoteStore) {
        this.remoteStore = remoteStore;
    }

    @Override
    public void getMovies(final Callback<List<Movie>> callback) {
        remoteStore.nowPlaying().get(callback);
    }

    @Override
    public void getMovieData(final long id, final Callback<Movie> callback) {
        remoteStore.movie(id).get(callback);
    }

    @Override
    public void findMovie(final Query query, final Callback<List<Movie>> callback) {
        remoteStore.findMovie(query).get(callback);
    }

}
