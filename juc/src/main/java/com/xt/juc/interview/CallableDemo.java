package com.xt.juc.interview;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("********come in Callable********");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 两个线程，一个main主线程，一个是A futureTask线程
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread t1 = new Thread(futureTask);
        t1.start();

        int result01 = 100;
        while (!futureTask.isDone()) {

        }
        int result02 = futureTask.get();

        int result = result01 + result02; // 要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，直到计算完成
        System.out.println("result: " + result);
    }
}
