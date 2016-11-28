package com.mokinjazz.and.martian.presentation.di.module;

import android.content.Context;
import android.util.Log;

import com.mokinjazz.and.martian.data.model.repository.MovieRepository;
import com.mokinjazz.and.martian.presentation.presenter.SingleMoviePresenter;
import com.mokinjazz.and.martian.presentation.presenter.MovieListPresenter;
import com.mokinjazz.and.martian.presentation.service.ImageLoader;
import com.mokinjazz.and.martian.presentation.service.impl.ImageLoaderImpl;
import com.mokinjazz.and.martian.presentation.view.model.MovieModelTransformer;

/**
 * Created by vvasilyev on 2/21/16.
 */
public class TestUserModule extends UserModule{

    private MovieListPresenter movieListPresenter;

    private SingleMoviePresenter singleMoviePresenter;

    public TestUserModule(MovieListPresenter presenter) {
        this.movieListPresenter = presenter;
    }

    public TestUserModule(SingleMoviePresenter presenter) {
        this.singleMoviePresenter = presenter;
    }

    public MovieListPresenter providesMovieListPresenter(MovieRepository repository, MovieModelTransformer transformer) {

        return movieListPresenter;
    }

    @Override
    public SingleMoviePresenter providesMovieDetailsPresenter(MovieRepository repository, MovieModelTransformer transformer) {
        Log.d("providesMDPresenter", "test_1");
        return singleMoviePresenter;
    }

    public ImageLoader providesImageLoader(Context context) {
        return new ImageLoaderImpl(context);
    }
}
