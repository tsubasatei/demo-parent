package com.xt.juc.interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 1 队列
 *
 * 2 阻塞队列
 *  2.1 阻塞队列有没有好的一面
 *  2.2 不得不阻塞，如何管理
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a")); // true
        System.out.println(blockingQueue.add("b")); // true
        System.out.println(blockingQueue.add("c")); // true
//        System.out.println(blockingQueue.add("X")); // java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.element()); // 队首元素

        System.out.println(blockingQueue.remove()); // a
        System.out.println(blockingQueue.remove()); // b
        System.out.println(blockingQueue.remove()); // c
//        System.out.println(blockingQueue.remove()); // java.util.NoSuchElementException
    }
}
