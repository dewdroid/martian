package com.mokinjazz.and.martian.presentation.presenter.impl;

import android.test.AndroidTestCase;

import com.mokinjazz.and.martian.data.model.Movie;
import com.mokinjazz.and.martian.data.model.repository.MovieRepository;
import com.mokinjazz.and.martian.presentation.view.SingleMovieView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;
import com.mokinjazz.and.martian.presentation.view.model.MovieModelTransformer;

import org.mockito.Mockito;

/**
 * Created by vvasilyev on 6/18/16.
 */
public class SingleMoviePresenterTest extends AndroidTestCase{

    private SingleMoviePresenterImpl presenter;

    private MovieRepository repository;

    private MovieModelTransformer transformer;

    private SingleMovieView view;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = Mockito.mock(MovieRepository.class);
        transformer = Mockito.mock(MovieModelTransformer.class);
        presenter = new SingleMoviePresenterImpl(repository, transformer);
        view = Mockito.mock(SingleMovieView.class);
        presenter.attach(view);
    }

    public void testGetMovieLoading() {
        presenter.movieData(1);

        Mockito.verify(view).showLoading();
        Mockito.verify(repository).getMovieData(Mockito.eq(1L), Mockito.any(MovieRepository.Callback.class));
    }

    public void testCallback() {
        MovieModel model = new MovieModel();
        Movie movie = new Movie("The best movie ever");

        presenter.callback.onOk(movie);
        Mockito.verify(transformer).transform(movie);
        Mockito.when(transformer.transform(movie)).thenReturn(model);

        Mockito.verify(view).renderMovie(model);
    }

    public void testCallbackError() {
        presenter.callback.onError(new Exception("something wrong"));

        Mockito.verify(view).showError(Mockito.anyString());
    }
}
