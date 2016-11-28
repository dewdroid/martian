package com.mokinjazz.and.martian.presentation.presenter.base;

import com.mokinjazz.and.martian.presentation.view.base.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by vvasilyev on 11/3/15.
 */
public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V>{

    WeakReference<V> view;

    V nullView;

    public MvpBasePresenter(V nullView) {
        if (nullView == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
        this.nullView = nullView;
    }

    @Override
    public void attach(V view) {
        this.view = new WeakReference<V>(view);
    }

    @Override
    public void detach(V view) {
        this.view = null;
    }

    @Override
    public V view() {
        if (!isViewAttached()) {
            return nullView();
        }
        return view.get();
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    protected V nullView() {
        return nullView;
    };
}
