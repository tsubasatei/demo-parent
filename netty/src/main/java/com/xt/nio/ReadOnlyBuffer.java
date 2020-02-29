package com.xt.nio;

import java.nio.ByteBuffer;

/**
 * 将一个普通 Buffer 转成只读 Buffer
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        // 创建一个 Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i < 10; i++) {
            byteBuffer.put((byte) i);
        }

        // 读取
        byteBuffer.flip();

        // 得到一个只读的 Buffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        // class java.nio.HeapByteBufferR
        System.out.println(readOnlyBuffer.getClass());

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        // java.nio.ReadOnlyBufferException
        readOnlyBuffer.put((byte) 100);
    }
}
