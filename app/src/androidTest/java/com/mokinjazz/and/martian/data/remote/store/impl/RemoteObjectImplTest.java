package com.mokinjazz.and.martian.data.remote.store.impl;

import com.mokinjazz.and.martian.data.remote.store.impl.RemoteObjectImpl;
import com.mokinjazz.and.martian.data.remote.store.impl.task.DeferrableTask;
import com.mokinjazz.and.martian.data.remote.store.impl.task.Task;
import com.mokinjazz.and.martian.data.remote.transformer.Transformer;
import com.mokinjazz.and.martian.data.remote.store.JobExecutor;
import com.mokinjazz.and.martian.data.remote.store.RemoteObject;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vvasilyev on 11/9/16.
 */

public class RemoteObjectImplTest {

    @Test
    public void testDeferrableTaskReturnsRemoteObject() {
        DeferrableTask task = new DeferrableTask.performingNothing<>();
        RemoteObject remoteObject = task.deferrable();
        assertThat(remoteObject, is(notNullValue()));
        assertThat(remoteObject, is(instanceOf(RemoteObjectImpl.class)));
        RemoteObjectImpl objectImpl = (RemoteObjectImpl) remoteObject;

        assertThat(objectImpl.task, is(sameInstance(task)));
        assertThat(objectImpl.jobExecutor, is(notNullValue()));
        assertThat(objectImpl.transformer, is(notNullValue()));
    }


    @Test
    public void testRemoteObjectFailureCase() {
        Exception exception = new Exception("Test exception");
        Task<Object> failureTask = () -> {
            throw exception;
        };

        RemoteObjectImpl<String> remoteObject  = new RemoteObjectImpl<String>(failureTask);
        remoteObject.jobExecutor = new TestJobExecutor();

        RemoteObject.Callback<String> callback = Mockito.mock(RemoteObject.Callback.class);

        remoteObject.get(callback);
        Mockito.verify(callback).onError(exception);
    }

    @Test
    public void testRemoteObjectOkCase() {
        String result = "Ok";
        Task<Object> okTask = () -> result;

        RemoteObjectImpl<String> remoteObject  = new RemoteObjectImpl<String>(okTask);
        remoteObject.jobExecutor = new TestJobExecutor();
        remoteObject.transformer = new Transformer() {
            @Override
            public <ENTITY, MODEL> MODEL transform(ENTITY entity) {
                return (MODEL) entity;
            }
        };

        RemoteObject.Callback<String> callback = Mockito.mock(RemoteObject.Callback.class);

        remoteObject.get(callback);
        Mockito.verify(callback).onOk(result);
    }

    /**
     * Test executor. Just executes jobs provided
     */
    class TestJobExecutor implements JobExecutor {

        @Override
        public void runAsync(Runnable job) {job.run();}

        @Override
        public void runOnUi(Runnable job) {job.run();}
    }
}
