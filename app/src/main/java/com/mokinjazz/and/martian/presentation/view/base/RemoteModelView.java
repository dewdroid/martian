package com.mokinjazz.and.martian.presentation.view.base;

/**
 * Created by vvasilyev on 11/8/15.
 */
public interface RemoteModelView {

    void showLoading();

    void dismissLoading();

    void showError(String message);

    class noop implements RemoteModelView {

        @Override
        public void showLoading() {

        }

        @Override
        public void dismissLoading() {

        }

        @Override
        public void showError(String message) {

        }
    }
}
