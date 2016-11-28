package com.mokinjazz.and.martian.data.remote;

import com.mokinjazz.and.martian.data.model.Movie;

/**
 * Created by vvasilyev on 11/5/15.
 */
public interface MovieCache {

    void put(long id, Movie movie);
}
