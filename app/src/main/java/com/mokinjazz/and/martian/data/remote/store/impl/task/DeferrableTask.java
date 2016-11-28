package com.mokinjazz.and.martian.data.remote.store.impl.task;

import com.mokinjazz.and.martian.data.remote.store.impl.RemoteObjectImpl;
import com.mokinjazz.and.martian.data.remote.store.RemoteObject;

/**
 *  Task providing {@link RemoteObjectImpl} which will allow task
 *  to {@link #perform()} in background
 *
 * @param <T>
 */
public abstract class DeferrableTask<T> implements Task<T> {
    public <K> RemoteObject<K> deferrable() {
        return new RemoteObjectImpl<>(this);
    }

    /**
     *   Simple {@link DeferrableTask} implementation which {@link #perform()} does nothing
     *
     * @param <T>
     */
    public static class performingNothing<T> extends DeferrableTask<T> {

        @Override
        public T perform() throws Exception {
            return null;
        }
    }
}
