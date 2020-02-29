package com.xt.nio;

import java.nio.IntBuffer;

/**
 * 举例说明Buffer的使用
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个Buffer，大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        // 向buffer存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        // 从buffer读取数据
        intBuffer.flip(); // 将buffer转换，读写切换
        /*
        public final Buffer flip() {
            limit = position;
            position = 0;
            mark = -1;
            return this;
        }
        */

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
