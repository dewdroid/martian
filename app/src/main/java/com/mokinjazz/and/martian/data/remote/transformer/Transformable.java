package com.mokinjazz.and.martian.data.remote.transformer;

/**
 * Created by vvasilyev on 3/20/16.
 */
public abstract class Transformable <SOURCE, RESULT>{

    public abstract RESULT create(SOURCE from);

    public Transformable<SOURCE, RESULT> register(TransformableRegistry transformableRegistry, Class<SOURCE> clazz) {
        transformableRegistry.register(this, clazz);
        return this;
    }

}
