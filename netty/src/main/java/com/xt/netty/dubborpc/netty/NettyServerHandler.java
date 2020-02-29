package com.xt.netty.dubborpc.netty;

import com.xt.netty.dubborpc.customer.ClientBootstrap;
import com.xt.netty.dubborpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String mes = msg.toString();
        // 获取客户端发送的消息，并调用服务
        System.out.println("msg = " + mes);
        // 客户端在调用服务器的api 时，我们需要定义一个协议
        // 比如我们要求 每次发消息是都必须以某个字符串开头 "HelloService#hello#你好"
        if (mes.startsWith(ClientBootstrap.providerName)) {
            String result = new HelloServiceImpl().hello(mes.substring(ClientBootstrap.providerName.length()));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
