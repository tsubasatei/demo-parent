package com.xt.netty.dubborpc.provider;

import com.xt.netty.dubborpc.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String mes) {
        System.out.println("收到客户端消息 = " + mes);
        if (mes != null) {
            return "你好客户端，我已收到你的消息 [" + mes + "]";
        } else {
            return "你好客户端，我已收到你的消息 ";
        }
    }
}
