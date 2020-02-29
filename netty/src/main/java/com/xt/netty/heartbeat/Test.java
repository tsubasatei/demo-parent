package com.xt.netty.heartbeat;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        long startTime = System.nanoTime(); // 纳秒，10亿分之一秒
        System.out.println("startTime = " + startTime); // 878031902689100
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        long endTime = System.nanoTime();
        System.out.println("endTime = " + endTime); // 878032904033600
        System.out.println("间隔 = " + (endTime - startTime)); // 1001344500
        ;
    }
}
