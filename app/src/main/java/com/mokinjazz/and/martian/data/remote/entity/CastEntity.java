package com.mokinjazz.and.martian.data.remote.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vvasilyev on 12/29/15.
 */
public class CastEntity {

    public String character;
    public String name;

    @SerializedName(value = "profile_path")
    public String profilePath;
}
