package com.mokinjazz.and.martian.presentation.di.module;

import android.app.Application;
import android.content.Context;

import com.mokinjazz.and.martian.data.model.repository.MovieRepository;
import com.mokinjazz.and.martian.data.model.repository.impl.MovieRepositoryImpl;
import com.mokinjazz.and.martian.data.remote.MovieCache;
import com.mokinjazz.and.martian.data.remote.MovieCacheImpl;
import com.mokinjazz.and.martian.data.remote.store.impl.JobExecutorImpl;
import com.mokinjazz.and.martian.data.remote.store.impl.MovieRemoteStoreImpl;
import com.mokinjazz.and.martian.data.remote.store.MovieRemoteStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vvasilyev on 2/19/16.
 */
@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    MovieRepository providesRepository(MovieRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    MovieRemoteStore proviesMovieStore(MovieRemoteStoreImpl store) {
        return store;
    }
    @Provides
    @Singleton
    MovieCache provideCache(MovieCacheImpl cache) {
        return cache;
    }

    @Provides
    @Singleton
    JobExecutorImpl providesJobExecutor() {
        return new JobExecutorImpl();
    }

}
