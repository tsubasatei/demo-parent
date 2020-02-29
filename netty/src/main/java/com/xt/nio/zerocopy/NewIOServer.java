package com.xt.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(inetSocketAddress);
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        int byteRead = 0;

        while (byteRead != -1) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            byteRead = socketChannel.read(buffer);
            buffer.rewind(); // 倒带 position = 0, mark 作废
        }
    }
}
