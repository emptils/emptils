package com.emptils.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.task.TaskExecutor;

/**
 * Created by Administrator on 2017/6/2.
 */
public class AutoRunTaskExecutor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private TestTask tt;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
            taskExecutor.execute(tt);
        }
    }
}
