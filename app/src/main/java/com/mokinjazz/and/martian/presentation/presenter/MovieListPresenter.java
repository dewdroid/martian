package com.mokinjazz.and.martian.presentation.presenter;

import com.mokinjazz.and.martian.presentation.presenter.base.MvpPresenter;
import com.mokinjazz.and.martian.presentation.view.MovieListView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;

/**
 * Created by vvasilyev on 11/4/15.
 */
public interface MovieListPresenter extends MvpPresenter<MovieListView> {

    void init();

    void viewMovie(MovieModel movie);

}
