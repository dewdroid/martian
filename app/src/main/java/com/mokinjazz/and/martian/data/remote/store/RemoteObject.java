package com.mokinjazz.and.martian.data.remote.store;

/**
 * Presents Remote Object. Retrieving could take a while, so must be performed asynchronously.
 *
 * @param <T>
 */
public interface RemoteObject<T> {

    /**
     *  Should perform actual loading and invoke {@link Callback#onOk(Object)} if succeed,
     *  otherwise - {@link Callback#onError(Exception)}
     *
     * @param callback
     */
    void get(Callback<T> callback);

    /**
     *  Callback
     *
     * @param <T>
     */
    interface Callback<T> {
        void onOk(T result);

        void onError(Exception e);
    }
}
