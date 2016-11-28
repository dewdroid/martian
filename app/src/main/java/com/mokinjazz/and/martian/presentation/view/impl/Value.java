package com.mokinjazz.and.martian.presentation.view.impl;

import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by vvasilyev on 5/28/16.
 */

public class Value {

    public static void inject(Object view, Object source) {
        for (Field field : view.getClass().getDeclaredFields()) {
            from annotation = field.getAnnotation(from.class);
            if (annotation != null) {
                String value = annotation.value();
                try {
                    Field f = source.getClass().getDeclaredField(value);

                    Object ui = field.get(view);
                    if (ui instanceof TextView) {
                        ((TextView) ui).setText(String.valueOf(f.get(source)));
                    }

                } catch (NoSuchFieldException e) {

                } catch (IllegalAccessException e) {

                }
            }
        }
    }

    @Retention(RUNTIME) @Target(FIELD)
    public @interface from {String value();}
}


