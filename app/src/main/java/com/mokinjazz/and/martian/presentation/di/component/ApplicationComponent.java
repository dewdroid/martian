package com.mokinjazz.and.martian.presentation.di.component;

import android.content.Context;

import com.mokinjazz.and.martian.data.remote.store.impl.Services;
import com.mokinjazz.and.martian.presentation.di.module.AppModule;
import com.mokinjazz.and.martian.data.model.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vvasilyev on 2/21/16.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    // expose
    MovieRepository movieRepository();

    Context contex();

    void inject(Services object);
}
