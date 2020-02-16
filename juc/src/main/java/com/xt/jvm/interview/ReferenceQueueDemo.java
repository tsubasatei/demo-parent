package com.xt.jvm.interview;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 引用队列
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);

        System.out.println(o1); // java.lang.Object@14ae5a5
        System.out.println(weakReference.get()); // java.lang.Object@14ae5a5
        System.out.println(referenceQueue.poll()); // null

        System.out.println("=======================");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1); // null
        System.out.println(weakReference.get()); // null
        System.out.println(referenceQueue.poll()); //java.lang.ref.WeakReference@7f31245a
    }
}
