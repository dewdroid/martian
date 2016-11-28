package com.mokinjazz.and.martian.data.remote.store.impl.task;

/**
 * Task interface
 *
 * @param <T>
 */
public interface Task<T> {
    T perform() throws Exception;
}
