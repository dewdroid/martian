package com.mokinjazz.and.martian.presentation.service;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Target;

/**
 * Created by vvasilyev on 6/18/16.
 */
public interface ImageLoader {

    void image(String url, ImageView target);
}
