package com.mokinjazz.and.martian.data.remote.transformer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vvasilyev on 3/20/16.
 */
public class TransformableRegistry {
    Map<Class, Transformable> transformer = new HashMap<>();

    public void register(Transformable transformable, Class clazz) {
        transformer.put(clazz, transformable);
    }

    public Transformable get(Class clazz) {
        return transformer.get(clazz);
    }
}
