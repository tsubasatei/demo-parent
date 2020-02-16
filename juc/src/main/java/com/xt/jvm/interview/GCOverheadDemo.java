package com.xt.jvm.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError:GC overhead limit exceeded
 * JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) { // java.lang.OutOfMemoryError: GC overhead limit exceeded
            System.out.println("i = " + i);
            e.printStackTrace();
            throw e;
        }
    }
}
