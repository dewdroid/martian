package com.mokinjazz.and.martian.data.remote.transformer;

/**
 * Created by vvasilyev on 6/25/16.
 */
public interface Transformer {

    <ENTITY, MODEL> MODEL transform(ENTITY entity);
}
