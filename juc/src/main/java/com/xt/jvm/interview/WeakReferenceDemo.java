package com.xt.jvm.interview;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1); // java.lang.Object@14ae5a5
        System.out.println(weakReference.get()); // java.lang.Object@14ae5a5

        o1 = null;
        System.gc();

        System.out.println(o1);  // null
        System.out.println(weakReference.get());  // null
    }
}
