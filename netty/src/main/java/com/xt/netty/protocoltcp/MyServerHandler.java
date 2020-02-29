package com.xt.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        // 接收数据并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务器接收到消息如下:");
        System.out.println("长度 = " + len);
        System.out.println("内容 = " + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务器接收到消息包数量 = " + (++this.count));

        // 回复消息
        byte[] responseContent = UUID.randomUUID().toString().getBytes("utf-8");
        int repsonseLen = responseContent.length;
        // 构建协议包
        MessageProtocol messageProtocol = new MessageProtocol().setLen(repsonseLen).setContent(responseContent);
        ctx.writeAndFlush(messageProtocol);
    }
}
