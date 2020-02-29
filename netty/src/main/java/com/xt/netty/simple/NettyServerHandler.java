package com.xt.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 自定义 Handler，需要继承 Netty 规定好的某个 HandlerAdapter（规范）
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取实际数据(读取客户端发送的消息)
     * 1. ChannelHandlerContext：上下文对象，含有管道 pipeline，通道 channel，地址
     * 2. Object msg：客户端发送的数据，默认 Object 类型
     *      将 msg 转成一个 ByteBuf, Netty提供的，不是 NIO 的 ByteBuffer
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /**
         * 假如这里有一个非常耗时长的业务 -> 异步执行 -> 提交该 channel 对应的 NioEventLoop 的 taskQueue 中
         * 解决方案1：用户程序自定义普通任务
         * 解决方案2：用户自定义任务 -> 该任务是提交到 scheduleTaskQueue
         */
        ctx.channel().eventLoop().execute(() -> {
            try { TimeUnit.SECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端2", CharsetUtil.UTF_8));
        });
        ctx.channel().eventLoop().execute(() -> { // 在上一任务基础上再加20s，同一线程
            try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端3", CharsetUtil.UTF_8));
        });

        ctx.channel().eventLoop().schedule(() -> {
            try { TimeUnit.SECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端4", CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);
        System.out.println("...go on...");

//        System.out.println("服务器读取线程：" + Thread.currentThread().getName());
//        System.out.println("server ctx = " + ctx);
        /*System.out.println("channel 和 pipeline 的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline(); // 本质是一个双线链表，入栈出栈

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址: " + channel.remoteAddress());*/
    }

    // 数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**
         * writeAndFlush：将数据写入到缓存，并刷新
         * 一般对发送的这个数据进行编码
         */
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端1", CharsetUtil.UTF_8));
    }

    // 处理异常，一般是需要关闭通道

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
