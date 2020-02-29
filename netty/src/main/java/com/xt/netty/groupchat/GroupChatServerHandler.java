package com.xt.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 使用一个hashMap 管理
    //public static Map<String, Channel> channels = new HashMap<String,Channel>();

    /**
     * 定义一个 channel 组，管理所有的 channel
     * GlobalEventExecutor.INSTANCE: 全局事件执行器，单例
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // handlerAdded: 表示连接建立，一旦连接，第一个被执行，将当前 channel 加入到 channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        /**
         * 将该客户加入聊天的信息推送给其它在线的客户端
         * 该方法会将 channelGroup 中所有的 channel 遍历，并发送消息，不需要自己遍历
         */
        channelGroup.writeAndFlush("[客户端] " + channel.remoteAddress() + " 加入聊天 " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
        channelGroup.add(channel);
    }

    // 断开连接，将xx客户端离开信息推送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开了\n");
        System.out.println("channelGroup size = " + channelGroup.size());
    }

    // 表示 channel 处于活动状态，提示 xx 上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线了~");
    }

    // 表示 channel 处于不活动状态，提示 xx 离线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离线了~");
    }

    // 读取数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 获取当前 channel
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel == ch) {
                ch.writeAndFlush("[自己]发送了消息： " + msg + "\n");
            } else {
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + " 发送了消息： " + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关闭通道
        ctx.close();
    }
}
