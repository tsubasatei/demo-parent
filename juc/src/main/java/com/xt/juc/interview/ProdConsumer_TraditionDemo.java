package com.xt.juc.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData { // 资源类
    private int data;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            // 1 判断
            while (data != 0) {
                // 等待，不能生产
                condition.await();
            }
            // 2 干活
            data++;
            System.out.println(Thread.currentThread().getName() + "\t" + data);
            // 3 唤醒通知
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (data == 0) {
                condition.await();
            }
            data--;
            System.out.println(Thread.currentThread().getName() + "\t" + data);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
/**
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 * 1 线程 操作(方法)  资源类
 * 2 判断 干活  通知
 * 3 防止虚假唤醒机制
 * wait/notify 用在 while 循环里
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.increment();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.decrement();
            }
        }, "BB").start();
    }
}
