package com.mokinjazz.and.martian.presentation.presenter;

import com.mokinjazz.and.martian.presentation.presenter.base.MvpPresenter;
import com.mokinjazz.and.martian.presentation.view.SingleMovieView;

/**
 * Created by vvasilyev on 11/8/15.
 */
public interface SingleMoviePresenter extends MvpPresenter<SingleMovieView> {

    void movieData(long id);

}
