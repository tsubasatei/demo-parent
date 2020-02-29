package com.xt.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder decode 被调用");
        // 需要将得到二进制字节码-> MessageProtocol 数据包(对象)
        int len = in.readInt();
        byte[] content = new byte[len];
        in.readBytes(content);
        // 封装成 MessageProtocol 对象，放入 out， 传递下一个handler业务处理
        MessageProtocol messageProtocol = new MessageProtocol().setContent(content).setLen(len);
        out.add(messageProtocol);
    }
}
