package com.xt.nio;

import java.nio.ByteBuffer;

/**
 * Buffer 支持类型化的 put 和 get，类型要匹配
 * 否则可能有 java.nio.BufferUnderflowException
 */
public class ByteBufferPutGet {
    public static void main(String[] args) {
        // 创建一个 Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        // 类型化方式放入数据
        byteBuffer.putInt(10);
        byteBuffer.putLong(9);
        byteBuffer.putChar('s');
        byteBuffer.putShort((short) 4);

        // 取出
        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
    }
}
