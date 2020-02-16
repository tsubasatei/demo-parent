package com.xt.juc.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Print {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int flag = 1; // A:1, B:2, C:3

    public void printA(int count) {
        lock.lock();
        try {
            while (flag != 1) {
                conditionA.await();
            }
            for (int i = 1; i <= count ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t print A 第 " + i + " 次");
            }
            flag = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(int count) {
        lock.lock();
        try {
            while (flag != 2) {
                conditionB.await();
            }
            for (int i = 1; i <= count ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t print B 第 " + i + " 次");
            }
            flag = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(int count) {
        lock.lock();
        try {
            while (flag != 3) {
                conditionC.await();
            }
            for (int i = 1; i <= count ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t print C 第 " + i + " 次");
            }
            flag = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
/**
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA 打印5次， BB 打印10次， CC打印15次
 * 紧接着
 * AA 打印5次， BB 打印10次， CC打印15次 。。。
 * 来10轮
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        Print print = new Print();

        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                print.printA(5);
            }, "A").start();
            new Thread(() -> {
                print.printB(10);
            }, "B").start();
            new Thread(() -> {
                print.printC(15);
            }, "C").start();
        }
    }
}
