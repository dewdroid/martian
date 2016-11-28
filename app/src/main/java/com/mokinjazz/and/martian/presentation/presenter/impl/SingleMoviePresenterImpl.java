package com.mokinjazz.and.martian.presentation.presenter.impl;

import android.util.Log;

import com.mokinjazz.and.martian.data.model.Movie;
import com.mokinjazz.and.martian.data.model.Credits;
import com.mokinjazz.and.martian.data.model.repository.MovieRepository;
import com.mokinjazz.and.martian.presentation.di.PerActivity;
import com.mokinjazz.and.martian.presentation.presenter.SingleMoviePresenter;
import com.mokinjazz.and.martian.presentation.presenter.base.MvpBasePresenter;
import com.mokinjazz.and.martian.presentation.view.SingleMovieView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;
import com.mokinjazz.and.martian.presentation.view.model.MovieModelTransformer;

import javax.inject.Inject;

import static com.mokinjazz.and.martian.presentation.view.SingleMovieView.SAFE_NULL;

/**
 * Created by vvasilyev on 11/8/15.
 */
@PerActivity
public class SingleMoviePresenterImpl extends MvpBasePresenter<SingleMovieView> implements SingleMoviePresenter {

    MovieRepository movieRepository;

    MovieModelTransformer modelTransformer;

    MovieRepository.Callback<Movie> callback = new MovieRepository.Callback<Movie>() {
        @Override
        public void onOk(Movie result) {
            showMovieInView(result);
        }

        @Override
        public void onError(Exception e) {
            showErrorInView(e.getMessage());
        }
    };

    @Inject
    public SingleMoviePresenterImpl(MovieRepository movieRepository, MovieModelTransformer modelTransformer) {
        super(SAFE_NULL);
        this.movieRepository = movieRepository;
        this.modelTransformer = modelTransformer;
    }

    private void showErrorInView(String message) {
        view().showError(message);
    }

    private void showMovieInView(Movie movie) {
        Log.d("Presenter", movie.toString());
        view().dismissLoading();

        MovieModel model = modelTransformer.transform(movie);
        view().renderMovie(model);
        view().renderCredits(Credits._(model.actorAndCharacters));
    }

    @Override
    public void movieData(long id) {
        view().showLoading();
        movieRepository.getMovieData(id, callback);
    }
}
