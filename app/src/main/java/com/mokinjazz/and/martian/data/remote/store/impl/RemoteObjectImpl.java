package com.mokinjazz.and.martian.data.remote.store.impl;

import com.mokinjazz.and.martian.data.remote.store.JobExecutor;
import com.mokinjazz.and.martian.data.remote.store.RemoteObject;
import com.mokinjazz.and.martian.data.remote.store.impl.task.Task;
import com.mokinjazz.and.martian.data.remote.transformer.Transformer;

/**
 *
 * @param <T>
 */
public class RemoteObjectImpl<T> implements RemoteObject<T> {

     JobExecutor jobExecutor = Services.instance.jobExecutor;

     Transformer transformer = Services.instance.transformer;

     Task task;

     public <P> RemoteObjectImpl(Task<P> task){
         this.task = task;
     }

     @Override
     public void get(Callback<T> callback) {
         jobExecutor.runAsync(() -> {
             try {
                 final T result = transformer.transform(task.perform());
                 jobExecutor.runOnUi(() -> callback.onOk(result));
             } catch (final Exception e) {
                 jobExecutor.runOnUi(() -> callback.onError(e));
             }
         });
     }
}
