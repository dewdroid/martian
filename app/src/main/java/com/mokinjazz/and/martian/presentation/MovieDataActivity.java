package com.mokinjazz.and.martian.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mokinjazz.and.martian.presentation.view.impl.SingleMovieFragment;

import static android.view.WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
import static android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;

public class MovieDataActivity extends AppCompatActivity {

    private static final String MOVIE_ID = MovieDataActivity.class.getSimpleName() + ".movie_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_data);

        long id = getIntent().getLongExtra(MOVIE_ID, -1);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, SingleMovieFragment.instance(id), SingleMovieFragment.TAG)
                .commit();

        if (BuildConfig.DEBUG) {
            debugConfig();
        }
    }

    public void debugConfig() {
        getWindow().addFlags(FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(FLAG_TURN_SCREEN_ON);
    }

    public static void start(Context context, long id) {
        context.startActivity(getIntent(context, id));
    }

    public static Intent getIntent(Context context, long id) {
        Intent intent = new Intent(context, MovieDataActivity.class);
        intent.putExtra(MOVIE_ID, id);
        return intent;
    }

}
