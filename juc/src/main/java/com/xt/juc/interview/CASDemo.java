package com.xt.juc.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS
 * 1. CAS 是什么？==》compareAndSet 比较并交换
 *
 *
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2020) + "\t current data: " + atomicInteger.get()); // true	 current data: 2020
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data: " + atomicInteger.get()); // false	 current data: 2020
    }
}
