package com.mokinjazz.and.martian.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by vvasilyev on 10/28/15.
 */
public class MovieListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MovieListActivity.class);
    }
}
