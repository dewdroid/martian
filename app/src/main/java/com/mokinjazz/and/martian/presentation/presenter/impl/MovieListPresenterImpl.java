package com.mokinjazz.and.martian.presentation.presenter.impl;

import com.mokinjazz.and.martian.presentation.di.PerActivity;
import com.mokinjazz.and.martian.data.model.Movie;
import com.mokinjazz.and.martian.data.model.repository.MovieRepository;
import com.mokinjazz.and.martian.presentation.presenter.MovieListPresenter;
import com.mokinjazz.and.martian.presentation.presenter.base.MvpBasePresenter;
import com.mokinjazz.and.martian.presentation.view.MovieListView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;
import com.mokinjazz.and.martian.presentation.view.model.MovieModelTransformer;

import java.util.List;

import javax.inject.Inject;

import static com.mokinjazz.and.martian.presentation.view.MovieListView.SAFE_NULL;

/**
 * Created by vvasilyev on 11/3/15.
 */
@PerActivity
public class MovieListPresenterImpl extends MvpBasePresenter<MovieListView> implements MovieListPresenter {

    private MovieRepository movieRepository;

    private MovieModelTransformer modelTransformer;

    private MovieRepository.Callback<List<Movie>> movieListCallback = new MovieRepository.Callback<List<Movie>>() {
        @Override
        public void onOk(List<Movie> result) {
            showMovieCollectionInView(result);
        }

        @Override
        public void onError(Exception e) {
            showErrorInView(e.getMessage());
        }
    };

    @Inject
    public MovieListPresenterImpl(MovieRepository movieRepository, MovieModelTransformer modelTransfomer) {
        super(SAFE_NULL);
        this.movieRepository = movieRepository;
        this.modelTransformer = modelTransfomer;
    }

    @Override
    public void init() {
        view().showLoading();
        movieRepository.getMovies(movieListCallback);
    }

    private void showMovieCollectionInView(List<Movie> collection) {
        List<MovieModel> modelCollection = modelTransformer.transform(collection);
        view().dismissLoading();
        view().renderMovieLIst(modelCollection);
    }

    /**
     * todo implement
     * @param message
     */
    private void showErrorInView(String message) {
        view().showError(message);
    }

    @Override
    public void viewMovie(MovieModel movie) {
        view().viewMovie(movie);
    }

}
