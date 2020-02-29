package com.xt.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端：
 * 1. telnet 127.0.0.1 6666
 * 2. ctrl+]
 * 3. send 字符串
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        /**
         * 线程池机制思路：
         * 1. 创建一个线程池
         * 2. 如果有客户端连接，就创建一个线程，与之通讯(单独写一个方法)
         */
        ExecutorService threadPool = Executors.newCachedThreadPool();
        
        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while (true) {
            System.out.println("线程信息：id = " + Thread.currentThread().getId() +
                    ",\t name = " + Thread.currentThread().getName());
            // 监听，等待客户端连接
            System.out.println("等待连接accept。。。");
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            // 创建一个线程，与之通讯
            threadPool.execute(() -> {
                try {
                    handler(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    // 编写一个handler方法，和客户端通讯
    public static void handler(Socket socket) throws IOException {
        try {
            System.out.println("线程信息：id = " + Thread.currentThread().getId() +
                    ",\t name = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过socket获取输入流
            InputStream is = socket.getInputStream();
            // 循环读取客户端发送的数据
            while (true) {
                System.out.println("线程信息：id = " + Thread.currentThread().getId() +
                        ",\t name = " + Thread.currentThread().getName());
                System.out.println("read...");
                int read = is.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read)); // 输出客户端发送的数据
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
