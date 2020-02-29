package com.xt.netty.dubborpc.publicinterface;

/**
 * 服务提供方 和 消费方 都需要
 */
public interface HelloService {
    String hello(String mes);
}
