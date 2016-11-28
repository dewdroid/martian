package com.mokinjazz.and.martian.presentation.view;

import com.mokinjazz.and.martian.data.model.Credits;
import com.mokinjazz.and.martian.presentation.view.base.MvpView;
import com.mokinjazz.and.martian.presentation.view.base.RemoteModelView;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;

/**
 * Created by vvasilyev on 11/8/15.
 */
public interface SingleMovieView extends RemoteModelView, MvpView {

    /**
     *  NULL implementation. Methods do nothing
     */
    SingleMovieView SAFE_NULL = new noop();

    /**
     *  Render movie
     *
     * @param model
     */
    void renderMovie(MovieModel model);

    /**
     *
     * @param credit
     */
    void renderCredits(Credits credit);

    class noop extends RemoteModelView.noop implements SingleMovieView {

        @Override
        public void renderMovie(MovieModel model) {

        }

        @Override
        public void renderCredits(Credits credit) {

        }
    }
}
