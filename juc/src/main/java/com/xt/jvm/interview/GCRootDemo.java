package com.xt.jvm.interview;

public class GCRootDemo {

//    private static GCRootDemo2 t2;
//    private static final GCRootDemo3 t3 = new GCRootDemo3();

    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }
}
