package com.mokinjazz.and.martian.presentation.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mokinjazz.and.martian.presentation.MovieDataActivity;
import com.mokinjazz.and.martian.presentation.R;
import com.mokinjazz.and.martian.presentation.presenter.MovieListPresenter;
import com.mokinjazz.and.martian.presentation.view.base.BaseMvpFragment;
import com.mokinjazz.and.martian.presentation.view.MovieListView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Fragment displaying list
 *
 * Created by vvasilyev on 11/3/15.
 */
public class MovieListFragment extends BaseMvpFragment<MovieListPresenter> implements MovieListView, MovieListAdapter.OnClickListener {

    @Inject
    MovieListPresenter presenter;

    @Bind(R.id.movie_list_view) RecyclerView recyclerView;

    @Bind(R.id.data_looking_up) ContentLoadingProgressBar progressBar;
    @Bind(R.id.fab) FloatingActionButton fab;

    MovieListAdapter adapter;

    @Override
    protected MovieListPresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmant_movie_list, container, false);
    }

    @Override
    protected void initialize(View view) {
        adapter = new MovieListAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getComponent().inject(this);
        getPresenter().attach(this);
        getPresenter().init();
    }

    @Override
    public void renderMovieLIst(List<MovieModel> list) {
        adapter.display(list);
    }

    /**
     * todo
     */
    @Override
    public void showLoading() {
    }

    /**
     *
     */
    @Override
    public void dismissLoading() {
        progressBar.hide();
    }

    /**
     * todo
     * @param message
     */
    @Override
    public void showError(String message) {

    }

    @Override
    public void viewMovie(MovieModel movie) {
        MovieDataActivity.start(getContext(), movie.id);
    }

    @Override
    public void onClick(MovieModel movie) {
        getPresenter().viewMovie(movie);
    }
}
