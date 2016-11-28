package com.mokinjazz.and.martian.presentation.view;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.mokinjazz.and.martian.presentation.MovieListActivity;
import com.mokinjazz.and.martian.presentation.R;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vvasilyev on 2/22/16.
 */
public class MovieListActivityTest extends ActivityInstrumentationTestCase2<MovieListActivity>{
    private MovieListActivity movieListActivity;

    public MovieListActivityTest() {
        super(MovieListActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityIntent(MovieListActivity.getIntent(getInstrumentation().getTargetContext()));
        movieListActivity = getActivity();
    }

    public void testContainsMovieListFragment() {
        Fragment fragment = movieListActivity.getSupportFragmentManager()
                .findFragmentById(R.id.movie_list_fragment);
        assertThat(fragment, is(notNullValue()));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
