package com.xt.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        File file = new File("d:\\file01.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("d:\\file02.txt");
        FileChannel fosChannel = fos.getChannel();

        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear(); // 清空buffer，重置
            int read = fileChannel.read(byteBuffer);
            if (read == -1) { // 表示读完
                break;
            }
            // 将buffer中的数据写入到fosChannel
            byteBuffer.flip();
            fosChannel.write(byteBuffer);
        }
        fis.close();
        fos.close();
    }
}
