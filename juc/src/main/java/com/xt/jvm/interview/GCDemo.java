package com.xt.jvm.interview;

import java.util.Random;

/**
 * 垃圾回收器
 * 1   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC   (DefNew + Tenured)
 * 2   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC   (DefNew + Tenured)
 *      Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector
 *      is deprecated and will likely be removed in a future release
 * 3   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC   (PSYoungGen + ParOldGen)
 * 4
 * 4.1 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC   (PSYoungGen + ParOldGen)
 * 4.2 不加就是默认UseParallelGC
 *     -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags   (PSYoungGen + ParOldGen)
 * 5   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC  (par new generation + concurrent mark-sweep)
 * 6   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC  后面单独讲解G1
 * 7   (理论知道即可，实际中java8已经被优化掉了，没有了)
 *     -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 *     Error: Could not create the Java Virtual Machine.
 *     Error: A fatal exception has occurred. Program will exit.
 *     Unrecognized VM option 'UseSerialOldGC'
 *     Did you mean '(+/-)UseSerialGC'?
 */
public class GCDemo {
    public static void main(String[] args) {
        System.out.println("*****GCDemo hello");
        try {
            String str = "zn";
            while (true) {
                /**
                 * 会随机生成一个整数，这个整数的范围就是int类型的范围-2^31 ~ 2^31-1,
                 * 但是如果在nextInt()括号中加入一个整数a那么，这个随机生成的随机数范围就变成[0,a)。
                 */
                str += str + new Random().nextInt(77777777) + new Random().nextInt(88888888);
                str.intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
