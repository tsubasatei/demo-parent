package com.xt.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

// 群聊系统服务端
public class GroupChatServer {
    // 定义属性
    private Selector selector; // 选择器
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞
            listenChannel.configureBlocking(false);
            // 将 listenChannel 注册到 selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 监听
    public void listen () {
        System.out.println("监听线程:" + Thread.currentThread().getName());
        try {
            // 循环处理
            while (true) {
                int count = selector.select(2000);
                if (count > 0) { // 有事件处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 监听到 accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            // 提示
                            System.out.println(sc.getRemoteAddress() + " 上线");
                        }
                        if (key.isReadable()) { // 通道发生 read 事件，可读
                            // 处理读
                            readData(key);

                        }
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    // 读取客户端消息
    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            // 根据 count 的值做处理
            if (count > 0) {
                // 把缓冲区的数据转成字符串
                String msg = new String(buffer.array());
                System.out.println("from 客户端 ：" + msg);

                // 向其他的客户端转发消息
                sendInfo2OtherClients(msg, channel);
            }
        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了");
                // 取消这侧
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    // 转发消息给其它客户通道
    public void sendInfo2OtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中...");
        System.out.println("服务器转发数据给服务器线程:" + Thread.currentThread().getName());
        // 遍历所有注册到selector上的 SocketChannel
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != self) {
                SocketChannel destChannel = (SocketChannel) channel;
                // 将 msg 存储到 buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                // 将 buffer 的数据写入通道
                destChannel.write(buffer);
            }
        }

    }
    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }
}
