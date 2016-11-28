package com.mokinjazz.and.martian.presentation.di.module;

import android.content.Context;

import com.mokinjazz.and.martian.presentation.di.PerActivity;
import com.mokinjazz.and.martian.data.model.repository.MovieRepository;
import com.mokinjazz.and.martian.presentation.presenter.SingleMoviePresenter;
import com.mokinjazz.and.martian.presentation.presenter.MovieListPresenter;
import com.mokinjazz.and.martian.presentation.presenter.impl.SingleMoviePresenterImpl;
import com.mokinjazz.and.martian.presentation.presenter.impl.MovieListPresenterImpl;
import com.mokinjazz.and.martian.presentation.service.ImageLoader;
import com.mokinjazz.and.martian.presentation.service.impl.ImageLoaderImpl;
import com.mokinjazz.and.martian.presentation.view.model.MovieModelTransformer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vvasilyev on 2/21/16.
 */
@Module
public class UserModule {

    @Provides
    @PerActivity
    public MovieListPresenter providesMovieListPresenter(MovieRepository repository, MovieModelTransformer transformer) {
        return new MovieListPresenterImpl(repository, transformer);
    }

    @Provides
    @PerActivity
    public SingleMoviePresenter providesMovieDetailsPresenter(MovieRepository repository, MovieModelTransformer transformer) {
        return new SingleMoviePresenterImpl(repository, transformer);
    }

    @Provides
    @PerActivity
    public ImageLoader providesImageLoader(Context context) {
        return new ImageLoaderImpl(context);
    }
}
