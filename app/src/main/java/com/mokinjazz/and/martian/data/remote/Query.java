package com.mokinjazz.and.martian.data.remote;

/**
 * Created by Vladimir on 1/16/2016.
 */
public class Query {
    public String value;

    public static Query of(String query) {
        Query q = new Query();
        q.value = query;
        return q;
    }
}
