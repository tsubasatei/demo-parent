package com.xt.juc.interview;

public class SingletonDemo {

    public static void main(String[] args) {
        // 单线程（main线程的操作动作）
//        System.out.println(Singleton.getInstance() == Singleton.getInstance());
//        System.out.println(Singleton1.getInstance() == Singleton1.getInstance());
//
//        System.out.println();

        // 并发多线程，情况发生了很大的变化
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                Singleton1.getInstance();
            }, String.valueOf(i)).start();
        }
    }

}

class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法Singleton()");
    }

    public static Singleton getInstance() {
        return singleton;
    }
}

class Singleton1 {
    private static volatile Singleton1 instance;

    private Singleton1() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法Singleton1()");
    }

    /**
     * DCL（Double Check Lock 双端检索机制）
     * 在 synchronized 代码块前后都做判断
     * @return
     */
    public static /*synchronized*/ Singleton1 getInstance() {
        if (instance == null) {
            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}