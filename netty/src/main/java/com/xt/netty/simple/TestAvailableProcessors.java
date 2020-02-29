package com.xt.netty.simple;

import io.netty.util.NettyRuntime;

// 测试CPU核数
public class TestAvailableProcessors {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors());
    }
}
