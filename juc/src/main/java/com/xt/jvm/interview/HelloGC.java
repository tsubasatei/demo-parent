package com.xt.jvm.interview;

import java.util.concurrent.TimeUnit;

public class HelloGC {

    public static void main(String[] args) {
        System.out.println("*********HelloGC");
//        try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) { e.printStackTrace(); }
        /*long totalMemory = Runtime.getRuntime().totalMemory(); // 返回Java虚拟机中的内存总量
        long maxMemory = Runtime.getRuntime().maxMemory(); // 返回Java虚拟机视图使用的最大内存量
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)、" + (totalMemory/(double)1024/1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "(字节)、" + (maxMemory/(double)1024/1024) + "MB");*/
    }
}
