package com.mokinjazz.and.martian.data.model;

import java.util.Date;
import java.util.List;

/**
 * Created by vvasilyev on 11/3/15.
 */
public class Movie {

    public String title;

    public String imageUrl;

    public String backdropUrl;

    public String tagline;

    public String overview;
    public long budget;
    public long revenue;

    public long id;
    public Date releaseDate;
    public List<ActorAndCharacter> credits;

    public Movie(String title) {
        this.title = title;
    }
}
