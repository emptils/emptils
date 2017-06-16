package com.emptils.task;

import com.emptils.util.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/2.
 */
@Component
public class TestTask implements Runnable {

    @Autowired
    private HttpClient httpClient;

    @Override
    public void run() {

    }
}
