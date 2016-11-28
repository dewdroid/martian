package com.mokinjazz.and.martian.presentation.di.component;

import com.mokinjazz.and.martian.presentation.di.PerActivity;
import com.mokinjazz.and.martian.presentation.di.module.UserModule;
import com.mokinjazz.and.martian.presentation.view.impl.SingleMovieFragment;
import com.mokinjazz.and.martian.presentation.view.impl.MovieListFragment;

import dagger.Component;

/**
 * Created by vvasilyev on 2/21/16.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {UserModule.class})
public interface UserComponent {

    void inject(MovieListFragment fragment);

    void inject(SingleMovieFragment fragment);
}
