package com.mokinjazz.and.martian.data.remote.store.impl.task;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vvasilyev on 11/9/16.
 */
public class TaskBuilderTest {

    @Test
    public void testIfPerformIsCalled() throws Exception {
        TaskBuilder<String, Long> taskBuilder = Mockito.spy(new TaskBuilder.performingNothing<>());
        DeferrableTask<String> task = taskBuilder.by(11L);

        assertThat(task, is(notNullValue()));
        task.perform();

        Mockito.verify(taskBuilder).perform(11L);
    }

}
