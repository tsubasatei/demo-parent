package com.xt.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class NettyByteBuf02 {
    public static void main(String[] args) {
        // 创建ByteBuf
        ByteBuf buf = Unpooled.copiedBuffer("hello,world", Charset.forName("UTF-8"));
        if (buf.hasArray()) {
            byte[] content = buf.array();
            // 将content转成字符串
            System.out.println(new String(content, Charset.forName("UTF-8")));
            System.out.println("buf = " + buf); // buf = UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 11, cap: 33)
            System.out.println(buf.arrayOffset()); // 0
            System.out.println(buf.readerIndex()); // 0
            System.out.println(buf.writerIndex()); // 11
            System.out.println(buf.capacity()); // 33

            System.out.println((char)buf.readByte()); // 104, h

            int len = buf.readableBytes();
            System.out.println("可读字节len = " + len); // 10

            // 按某个范围读取
            System.out.println(buf.getCharSequence(0, 4, Charset.forName("UTF-8"))); // hell
            System.out.println(buf.getCharSequence(6, 5, Charset.forName("UTF-8"))); // world
        }
    }
}
