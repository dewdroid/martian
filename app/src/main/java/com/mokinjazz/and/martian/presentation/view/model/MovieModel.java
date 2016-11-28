package com.mokinjazz.and.martian.presentation.view.model;

import com.mokinjazz.and.martian.data.model.ActorAndCharacter;
import com.mokinjazz.and.martian.data.model.Movie;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 *  Movie representation which is ready to be displayed as is,
 *  i.e. everything got set to the format required by view
 *
 * Created by vvasilyev on 10/28/15.
 */
public class MovieModel {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public interface field {
        String title = "title";
        String tagline = "tagline";
        String overview = "overview";
        String budget = "budget";
        String revenue = "revenue";
        String releaseDate = "releaseDate";
    }

    public String title;

    public String imageUrl;

    public String backdropUrl;
    public long id;

    public String tagline;

    public String overview;
    public long budget;
    public long revenue;
    public String releaseDate;
    public String year;
    public List<ActorAndCharacter> actorAndCharacters;


    public MovieModel() {
    }

    public MovieModel(String title) {
        this.title = title;
    }

    public MovieModel(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public static MovieModel from(Movie source) {
        MovieModel target = new MovieModel();
        target.id = source.id;
        target.title = source.title;
        target.imageUrl = source.imageUrl;
        target.tagline = source.tagline;
        target.overview = source.overview;
        target.budget = source.budget;
        target.revenue = source.revenue;
        target.releaseDate = source.releaseDate != null ? DATE_FORMAT.format(source.releaseDate) : "";
        target.year = target.releaseDate.length() ==4 ? target.releaseDate.substring(0, 4): "?";
        target.actorAndCharacters = source.credits;
        return target;
    }

}
