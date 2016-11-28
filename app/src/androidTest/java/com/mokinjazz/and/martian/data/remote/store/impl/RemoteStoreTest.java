package com.mokinjazz.and.martian.data.remote.store.impl;

import com.mokinjazz.and.martian.data.remote.MovieCache;
import com.mokinjazz.and.martian.data.remote.entity.MovieEntity;
import com.mokinjazz.and.martian.data.remote.rest.RestApi;
import com.mokinjazz.and.martian.data.remote.rest.RetrofitRestApi;
import com.mokinjazz.and.martian.data.remote.store.impl.task.DeferrableTask;
import com.mokinjazz.and.martian.data.remote.store.impl.task.TaskBuilder;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;

/**
 * Created by vvasilyev on 6/19/16.
 */
public class RemoteStoreTest  {

    MovieRemoteStoreImpl store;

    MovieCache cache;

    @Before
    public void before() {
        cache = mock(MovieCache.class);
        store = new MovieRemoteStoreImpl(cache);
    }

    @Test
    public void testGetMovieTask() throws Exception {
        RestApi api = Mockito.mock(RestApi.class);
        store.Get.restApi = api;

        store.Get.movie.perform(11L);
        Mockito.verify(api).getMovie(11L);
    }


    @Test
    public void testGetMovie() throws RetrofitRestApi.RestException {
        DeferrableTask<MovieEntity> task = Mockito.mock(DeferrableTask.class);

        store.Get.movie = new TaskBuilder.performingNothing<MovieEntity, Long>() {
            @Override
            public DeferrableTask<MovieEntity> by(Long paramObject) {
                return task;
            }
        };
        store.movie(1);

        Mockito.verify(task).deferrable();
    }

}
