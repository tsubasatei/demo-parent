package com.xt.netty.inboundandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 入站的handler进行解码 MyByteToLongDecoder
//        pipeline.addLast(new MyByte2LongDecoder());
        pipeline.addLast(new MyByte2LongDecoder2());
        // 出站的handler进行编码
        pipeline.addLast(new MyLong2ByteEncoder());
        // 自定义的handler 处理业务逻辑
        pipeline.addLast(new MyServerHandler());
    }
}
