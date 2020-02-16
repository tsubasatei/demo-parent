package com.xt.juc.interview;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>(new HashSet<>());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }


    // java.util.ConcurrentModificationException

    /**
     * 1. 故障现象：java.util.ConcurrentModificationException
     * 2. 导致原因:
     *  并发争抢修改导致，参考花名册签名情况。
     *  一个人正在写入，另外一个人过来抢夺，导致数据不一致异常。并发修改异常。
     * 3. 解决方案：
     *      3.1 new Vector();
     *      3.2 Collections.synchronizedList(new ArrayList<>());
     *      3.3 new CopyOnWriteArrayList<>();
     * 4. 优化建议(同样的错误不犯第2次)
     */
    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <=30 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
