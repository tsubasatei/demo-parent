package com.xt.jvm.interview;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        byte[] bytes = new byte[50 * 1024 * 1024]; // 50MB
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
