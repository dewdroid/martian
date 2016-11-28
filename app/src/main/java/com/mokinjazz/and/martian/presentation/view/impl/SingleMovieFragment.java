package com.mokinjazz.and.martian.presentation.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mokinjazz.and.martian.presentation.Background;
import com.mokinjazz.and.martian.presentation.R;
import com.mokinjazz.and.martian.data.model.ActorAndCharacter;
import com.mokinjazz.and.martian.data.model.Credits;
import com.mokinjazz.and.martian.presentation.presenter.SingleMoviePresenter;
import com.mokinjazz.and.martian.presentation.service.ImageLoader;
import com.mokinjazz.and.martian.presentation.view.base.BaseMvpFragment;
import com.mokinjazz.and.martian.presentation.view.SingleMovieView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;

import javax.inject.Inject;

import butterknife.Bind;

import static com.mokinjazz.and.martian.presentation.R.*;
import static com.mokinjazz.and.martian.presentation.view.model.MovieModel.*;

/**
 * Created by vvasilyev on 11/8/15.
 */
public class SingleMovieFragment extends BaseMvpFragment<SingleMoviePresenter> implements SingleMovieView {

    public static final String TAG = SingleMovieFragment.class.getName() + "-tag";

    private long movieid;

    @Bind(R.id._d_loading) View loading;

    @Bind(R.id._d_title_tv) @Value.from(field.title) TextView title_tv;

    @Bind(R.id._d_tagline_tv) @Value.from(field.tagline) TextView tagline_tv;

    @Bind(R.id._d_overview_tv) @Value.from(field.overview) TextView overview_tv;

    @Bind(R.id._d_revenue_tv) @Value.from(field.revenue) TextView revenue_tv;

    @Bind(R.id._d_release_date_tv) @Value.from(field.releaseDate) TextView release_date_tv;

    @Bind(R.id._d_budget_tv) @Value.from(field.budget) TextView budget_tv;

    @Bind(R.id._d_background_iv) Background background;

    @Bind(R.id._d_cast_vg) ViewGroup casting_vg;

    @Inject
    SingleMoviePresenter presenter;

    @Inject
    ImageLoader imageLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_data, container, false);
    }

    @Override
    public void renderMovie(MovieModel model) {
        Value.inject(this, model);
        imageLoader.image(model.imageUrl, background);
    }

    @Override
    public void renderCredits(Credits credit) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (ActorAndCharacter actorAndCharacter : credit.actorAndCharacter) {
            View view = inflater.inflate(R.layout.list_cast_item, casting_vg, false);
            bind(view, actorAndCharacter);
            casting_vg.addView(view);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getComponent().inject(this);
        getPresenter().attach(this);
        getPresenter().movieData(movieid);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected SingleMoviePresenter getPresenter() {
        return presenter;
    }

    public void set(long id) {
        this.movieid = id;
    }

    public void bind(View view, ActorAndCharacter actorAndCharacter) {
        ((TextView) view.findViewById(id.cast_name_view)).setText(actorAndCharacter.actorName);
        ((TextView) view.findViewById(id.cast_character_view)).setText(actorAndCharacter.characterName);
    }

    public static SingleMovieFragment instance(long id) {
        SingleMovieFragment fragment = new SingleMovieFragment();
        fragment.set(id);
        return fragment;
    }

}
