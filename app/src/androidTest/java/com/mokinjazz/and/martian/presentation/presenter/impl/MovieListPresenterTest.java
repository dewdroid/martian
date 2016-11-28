package com.mokinjazz.and.martian.presentation.presenter.impl;

import android.test.AndroidTestCase;

import com.mokinjazz.and.martian.data.model.repository.MovieRepository;
import com.mokinjazz.and.martian.presentation.view.MovieListView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModelTransformer;

import org.mockito.Mockito;


/**
 * Created by vvasilyev on 11/4/15.
 */
public class MovieListPresenterTest extends AndroidTestCase{

    private MovieListPresenterImpl movieListPresenter;


    private MovieListView view;

    private MovieRepository movieRepository;

    private MovieModelTransformer modelTransformer;

    @Override
    public void setUp() throws Exception {

        view = Mockito.mock(MovieListView.class);
        movieRepository = Mockito.mock(MovieRepository.class);
        modelTransformer = Mockito.mock(MovieModelTransformer.class);
        movieListPresenter = new MovieListPresenterImpl(movieRepository, modelTransformer);
        movieListPresenter.attach(view);
    }

    public void testInitialize() throws Exception {
        movieListPresenter.init();
        Mockito.verify(view).showLoading();
    }
}
