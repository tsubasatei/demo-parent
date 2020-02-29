package com.xt.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "Hello, 我的梦想";
        // 创建一个输出流 -> channel
        FileOutputStream fos = new FileOutputStream("d:\\file01.txt");
        // 通过 FileOutputStream 获取对应的 FileChannel，真是类型是 FileChannelImpl
        FileChannel fileChannel = fos.getChannel();
        // 创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将 str 放入到 ByteBuffer
        byteBuffer.put(str.getBytes());
        // 对 ByteBuffer 进行flip
        byteBuffer.flip();
        // 将 ByteBuffer 数据写入到 FileChannel
        fileChannel.write(byteBuffer);
        fileChannel.close();
        fos.close();
    }
}
