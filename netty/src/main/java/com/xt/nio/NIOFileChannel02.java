package com.xt.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {
        // 创建文件的输入流
        File file = new File("d:\\file01.txt");
        FileInputStream fis = new FileInputStream(file);
        // 通过 FileInputStream 获取对应的 FileChannel，真是类型是 FileChannelImpl
        FileChannel fileChannel = fis.getChannel();
        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 将通道的数据读到 Buffer
        fileChannel.read(byteBuffer);
        // 将 Buffer 的字节数据转成 String
        System.out.println(new String(byteBuffer.array()));
        fis.close();
    }
}
