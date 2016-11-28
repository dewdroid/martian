package com.mokinjazz.and.martian.presentation.view.model;

import com.mokinjazz.and.martian.presentation.di.PerActivity;
import com.mokinjazz.and.martian.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by vvasilyev on 11/4/15.
 */
@PerActivity
public class MovieModelTransformer {

    @Inject
    public MovieModelTransformer() {
    }

    public List<MovieModel> transform(List<Movie> original) {
        List<MovieModel> list = new ArrayList<>();
        for (Movie movie : original) {
            list.add(transform(movie));
        }
        return list;
    }

    public MovieModel transform(Movie movie) {
        return MovieModel.from(movie);
    }
}
