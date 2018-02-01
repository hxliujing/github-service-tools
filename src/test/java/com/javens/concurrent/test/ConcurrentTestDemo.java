package com.javens.concurrent.test;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class ConcurrentTestDemo {
    @Test
    public void demo() throws ExecutionException, InterruptedException {
        System.out.println("====");
        ConcurrentTestUtil.concurrentTest(100, 2000,
            new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(10);
                    return "ok";
                }
            },
            new ResultHandler<String>(){
                @Override
                public void handle(String result) {
                    System.out.println(result);
                }
            },
        6000);
    }
}
