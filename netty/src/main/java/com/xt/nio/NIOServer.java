package com.xt.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 得到一个 Selector 对象. 真实类型 WindowSelectorImpl
        Selector selector = Selector.open();

        // 绑定一个端口 6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把 ServerSocketChannel 注册到 selector, 关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册后的 selectionKey 数量 = " + selector.keys().size());

        // 循环等待客户端连接
        while (true) {
            // 这里等待1秒，如果没有事件发生，返回
            if (selector.select(1000) == 0) { // 没有事件发生
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            /**
             * 如果select返回的>0, 就获取相关的selectionKey集合
             * 1. 如果返回的>0，表示已经获取到关注的事件
             * 2. selector.selectedKeys()返回关注事件的集合,
             *    通过 selectedKeys 反向获取通道
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys 的数量 = " + selectionKeys.size());
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                // 根据key对应的通道发生的事件做相应处理
                if (selectionKey.isAcceptable()) { // 如果是OP_ACCEPT，有新的客户端连接
                    // 该客户端生成一个 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个 socketChannel " + socketChannel.hashCode());
                    // 将 SocketChannel 设置成非阻塞
                    socketChannel.configureBlocking(false);
                    // 将 socketChannel 注册到 selector，关注事件为OP_READ,同时给 socketChannel 关联一个 Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("客户端连接后，注册的 selectionKey 数量 = " + selector.keys().size());
                }
                if (selectionKey.isReadable()) { // 发生 OP_READ
                    // 通过 selectionKey 反向获取到对应的 Channel
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 通过 selectionKey 获取到 channel 关联的 buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(buffer);
                    System.out.println("form 客户端: " + new String(buffer.array()));
                }
                // 手动从集合中移除当前的 selectionKey，防止重复操作
                keyIterator.remove();
            }
        }
    }
}
