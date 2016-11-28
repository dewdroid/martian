package com.mokinjazz.and.martian.data.remote.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vvasilyev on 11/5/15.
 */
public class MovieEntity {

    public long id;
    public String backdropPath;

    @SerializedName(value = "poster_path")
    public String posterPath;

    @SerializedName(value = "release_date")
    public String releaseDate;

    public String title;

    public String tagline;

    public String overview;
    public long budget;
    public long revenue;

    public CreditCollection credits;

}
