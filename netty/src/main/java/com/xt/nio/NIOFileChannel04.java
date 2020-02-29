package com.xt.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        // 创建相关流
        FileInputStream fis = new FileInputStream("madara.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");

        // 获取各个流对应的 FileChannel
        FileChannel srcCh = fis.getChannel();
        FileChannel destCh = fos.getChannel();

        // 使用 transferFrom 完成拷贝
        destCh.transferFrom(srcCh, 0, srcCh.size());

        // 关闭相关通道和流
        srcCh.close();
        destCh.close();
        fis.close();
        fos.close();
    }
}
