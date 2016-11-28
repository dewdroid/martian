package com.mokinjazz.and.martian.presentation.service.impl;

import android.content.Context;
import android.widget.ImageView;

import com.mokinjazz.and.martian.presentation.service.ImageLoader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

/**
 * Created by vvasilyev on 6/18/16.
 */
public class ImageLoaderImpl implements ImageLoader{

    Context context;

    @Inject
    public ImageLoaderImpl(Context context) {
        this.context = context;
    }

    @Override
    public void image(String url, ImageView target) {
        Picasso.with(context).load(url).into(target);
    }
}
