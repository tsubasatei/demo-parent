package com.xt.jvm.interview;

import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的直接堆内存" + VM.maxDirectMemory()/(double)1024/1024 + "MB");
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        ByteBuffer buffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
        // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
    }
}
