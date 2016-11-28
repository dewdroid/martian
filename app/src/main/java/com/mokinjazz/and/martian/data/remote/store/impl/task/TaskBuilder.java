package com.mokinjazz.and.martian.data.remote.store.impl.task;

/**
 * Created by vvasilyev on 11/9/16.
 */
public abstract class TaskBuilder<T, P> {

    public abstract T perform(P paramObject) throws Exception;

    public DeferrableTask<T> by(P paramObject) {
        return new DeferrableTask<T>() {

            @Override
            public T perform() throws Exception {
                return TaskBuilder.this.perform(paramObject);
            }
        };
    }

    public static class performingNothing<T, P> extends TaskBuilder<T, P> {

        @Override
        public T perform(P paramObject) throws Exception {
            return null;
        }
    }
}
