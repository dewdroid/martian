package com.mokinjazz.and.martian.data.remote.store.impl;

import com.mokinjazz.and.martian.data.model.Movie;
import com.mokinjazz.and.martian.data.remote.MovieCache;
import com.mokinjazz.and.martian.data.remote.Query;
import com.mokinjazz.and.martian.data.remote.entity.MovieCollection;
import com.mokinjazz.and.martian.data.remote.entity.MovieEntity;
import com.mokinjazz.and.martian.data.remote.rest.RestApi;
import com.mokinjazz.and.martian.data.remote.store.MovieRemoteStore;
import com.mokinjazz.and.martian.data.remote.store.RemoteObject;
import com.mokinjazz.and.martian.data.remote.store.impl.task.DeferrableTask;
import com.mokinjazz.and.martian.data.remote.store.impl.task.TaskBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by vvasilyev on 11/5/15.
 */
@Singleton
public class MovieRemoteStoreImpl implements MovieRemoteStore {

    private MovieCache cache;

    Tasks Get = new Tasks();

    static class Tasks {

        RestApi restApi = new Services().restApi;

        public TaskBuilder<MovieEntity, Long> movie = new TaskBuilder<MovieEntity, Long>() {
            @Override
            public MovieEntity perform(Long id) throws Exception {
                return restApi.getMovie(id);
            }
        };

        public TaskBuilder<MovieCollection, Query> movieList = new TaskBuilder<MovieCollection, Query>() {
            @Override
            public MovieCollection perform(Query query) throws Exception {
                return restApi.getCollection(query);
            }
        };

        public DeferrableTask<MovieCollection> nowPlaying = new DeferrableTask<MovieCollection>() {
            @Override
            public MovieCollection perform() throws Exception {
                return restApi.getCollection(null);
            }
        };
    }

    @Inject
    public MovieRemoteStoreImpl(MovieCache cache) {
        this.cache = cache;
    }

    @Override
    public RemoteObject<List<Movie>> nowPlaying() {
        return Get.nowPlaying.deferrable();
    }

    @Override
    public RemoteObject<Movie> movie(long id) {
        return Get.movie.by(id).deferrable();
    }

    @Override
    public RemoteObject<List<Movie>> findMovie(Query query) {
        return Get.movieList.by(query).deferrable();
    }

}
