package com.mokinjazz.and.martian.data.remote.transformer;

import com.mokinjazz.and.martian.data.model.Movie;
import com.mokinjazz.and.martian.data.model.ActorAndCharacter;
import com.mokinjazz.and.martian.data.remote.entity.CastEntity;
import com.mokinjazz.and.martian.data.remote.entity.CreditCollection;
import com.mokinjazz.and.martian.data.remote.entity.MovieCollection;
import com.mokinjazz.and.martian.data.remote.entity.MovieEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by vvasilyev on 11/5/15.
 */
@Singleton
public class RemoteEntityTransformer extends TransformableRegistry implements Transformer {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w1000";
    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    Transformable movieEntityMovieTransformable = new Transformable<MovieEntity, Movie>() {
        @Override
        public Movie create(MovieEntity from) {
            return transform(from);
        }
    }.register(this, MovieEntity.class);

    Transformable movieCollectionTransformable = new Transformable<MovieCollection, List<Movie>>() {
        @Override
        public List<Movie> create(MovieCollection from) {
            return transform(from);
        }
    }.register(this, MovieCollection.class);

    @Inject
    public RemoteEntityTransformer() {
    }

    @Override
    public <ENTITY, MODEL> MODEL transform(ENTITY entity) {
        return (MODEL) get(entity.getClass()).create(entity);
    }

    protected Movie transform(MovieEntity source) {
        Movie target = new Movie(source.title);
        target.id = source.id;
        target.backdropUrl = IMAGE_BASE_URL + source.backdropPath;
        target.imageUrl = POSTER_BASE_URL + source.posterPath;
        target.tagline = source.tagline;
        target.overview = source.overview;
        target.budget = source.budget;
        target.revenue = source.revenue;
        try {
            target.releaseDate = DATE_FORMAT.parse(source.releaseDate);
        } catch (ParseException e) {
            // ignore
        }

        target.credits = transform(source.credits);
        return target;
    }

    protected List<Movie> transform(MovieCollection collection) {
        List<Movie> list = new ArrayList<>();
        for (MovieEntity entity : collection.results) {
            list.add(transform(entity));
        }
        return list;
    }

    protected List<ActorAndCharacter> transform(CreditCollection collection) {
        List<ActorAndCharacter> list = new ArrayList<>();
        if (collection != null) {
            for (CastEntity entity : collection.cast) {
                list.add(transform(entity));
            }
        }

        return list;
    }

    private ActorAndCharacter transform(CastEntity entity) {
        ActorAndCharacter actorAndCharacter = new ActorAndCharacter();
        actorAndCharacter.actorName = entity.name;
        actorAndCharacter.characterName = entity.character;
        actorAndCharacter.actorPicture = POSTER_BASE_URL + entity.profilePath;
        return actorAndCharacter;
    }
}
