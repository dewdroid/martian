package com.mokinjazz.and.martian.presentation.presenter.base;

import com.mokinjazz.and.martian.presentation.view.base.MvpView;

/**
 * Created by vvasilyev on 11/3/15.
 */
public interface MvpPresenter<V extends MvpView> {
    void attach(V view);

    void detach(V view);

    V view();
}
