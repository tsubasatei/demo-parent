package com.xt.netty.dubborpc.customer;

import com.xt.netty.dubborpc.netty.NettyClient;
import com.xt.netty.dubborpc.publicinterface.HelloService;

import java.util.concurrent.TimeUnit;

public class ClientBootstrap {

    // 这里定义协议头
    public static String providerName = "HelloService#hello#";

    public static void main(String[] args) {
        // 创建一个消费者
        NettyClient customer = new NettyClient();

        // 创建代理对象
        HelloService helloService = (HelloService) customer.getBean(HelloService.class, providerName);

        for(;;) {
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            //通过代理对象调用服务提供者的方法(服务)
            String res = helloService.hello("你好 dubborpc netty");
            System.out.println("调用的结果 res = " + res);
        }

    }
}
