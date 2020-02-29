package com.xt.netty.inboundandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 加入一个出站的handler 对数据进行一个编码
        pipeline.addLast(new MyLong2ByteEncoder());
        // 入站的解码
//        pipeline.addLast(new MyByte2LongDecoder());
        pipeline.addLast(new MyByte2LongDecoder2());
        // 加入自定义的handler,处理业务
        pipeline.addLast(new MyClientHandler());

    }
}
