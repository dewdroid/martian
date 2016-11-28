package com.mokinjazz.and.martian.presentation.view;


import com.mokinjazz.and.martian.presentation.view.base.MvpView;
import com.mokinjazz.and.martian.presentation.view.base.RemoteModelView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;

import java.util.List;

/**
 * Created by vvasilyev on 11/3/15.
 */
public interface MovieListView extends RemoteModelView, MvpView {

    MovieListView SAFE_NULL = new noop();

    void renderMovieLIst(List<MovieModel> list);

    void viewMovie(MovieModel movie);

    class noop extends RemoteModelView.noop implements MovieListView{

        @Override
        public void renderMovieLIst(List<MovieModel> list) {

        }

        @Override
        public void viewMovie(MovieModel movie) {

        }
    }
}
