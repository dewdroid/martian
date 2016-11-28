package com.mokinjazz.and.martian.data.remote.store.impl;

import com.mokinjazz.and.martian.data.remote.transformer.RemoteEntityTransformer;
import com.mokinjazz.and.martian.data.remote.rest.RestApi;
import com.mokinjazz.and.martian.presentation.MartianApplication;

import javax.inject.Inject;

/**
 *
 *
 * Created by vvasilyev on 6/22/16.
 */
public class Services {
    @Inject
    public JobExecutorImpl jobExecutor;

    @Inject
    public RemoteEntityTransformer transformer;

    @Inject
    public RestApi restApi;

    public static final Services instance = new Services();

    public Services() {
        MartianApplication.getApplicationComponent().inject(this);
    }

}
