package com.xt.juc.interview.test;

/**
 * 先编译，在右键External Tools --> javap -c
 */
class T1 {
    volatile int n;

    public void add () {
        n++;
    }

}