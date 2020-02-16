package com.xt.juc.interview;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t持有锁" + lockA +  ", 尝试获得锁" + lockB);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t持有锁" + lockB +  ", 尝试获得锁" + lockA);
            }
        }
    }
}
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBB").start();

        /**
         * linux    ps -ef | grep xxx  ls -l
         * window下的java运行程序，也有类似ps的查看进程的命令，但是目前需要查看的只是java
         *      jps = java ps       jps -l      jstack 进程号
         */
    }
}
