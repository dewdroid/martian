package com.mokinjazz.and.martian.presentation.view.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mokinjazz.and.martian.presentation.MartianApplication;
import com.mokinjazz.and.martian.presentation.di.component.UserComponent;
import com.mokinjazz.and.martian.presentation.presenter.base.MvpPresenter;

import butterknife.ButterKnife;

/**
 * Created by vvasilyev on 11/3/15.
 */
public abstract class BaseMvpFragment<P extends MvpPresenter> extends Fragment implements MvpView {

    protected UserComponent getComponent() {
        return ((MartianApplication) getActivity().getApplication()).getUserComponent();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initialize(view);
    }

    protected void initialize(View view) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().detach(this);
    }

    protected abstract P getPresenter();

}
