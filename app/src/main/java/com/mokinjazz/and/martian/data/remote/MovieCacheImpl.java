package com.mokinjazz.and.martian.data.remote;

import com.mokinjazz.and.martian.data.model.Movie;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by vvasilyev on 12/6/15.
 */
@Singleton
public class MovieCacheImpl implements MovieCache{

    @Inject
    public MovieCacheImpl() {
    }

    @Override
    public void put(long id, Movie movie) {

    }
}
