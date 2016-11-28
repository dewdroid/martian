package com.mokinjazz.and.martian.data.remote.store;

import com.mokinjazz.and.martian.data.model.Movie;
import com.mokinjazz.and.martian.data.remote.Query;

import java.util.List;

/**
 * Created by vvasilyev on 12/6/15.
 */
public interface MovieRemoteStore {
    RemoteObject<List<Movie>> nowPlaying();

    RemoteObject<Movie> movie(long id);

    RemoteObject<List<Movie>> findMovie(Query query);

}
