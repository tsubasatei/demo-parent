package com.xt.juc.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现一个自旋锁：
 * 自旋锁的好处：循环比较获取直到成功为止，没有类似wait的阻塞
 * 通过CAS操作完成自旋锁，A线程先进来调用mylock方法自己持有锁5秒钟，
 * B随后进来后发现当前有现成持有锁，不是null，所以只能通过自旋等待，
 * 直到A释放后B随后抢到
 *
 * AA	 come in myLock()
 * BB	 come in myLock()
 * AA	 myUnLock()
 * BB	 myUnLock()
 */
public class SpinLockDemo {

    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in myLock()");
        while (!atomicReference.compareAndSet(null, thread)) {
            
        }
    }
    
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t myUnLock()");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        }, "AA").start();
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        }, "BB").start();
    }
}
