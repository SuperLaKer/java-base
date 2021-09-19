package aa.my.javaio.netty.b_chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 两个线程池默认线程数：CPU_CORE * 2, 这里指定为2
 * worker每个线程都有一个Selector, 每个线程对应多个clientChannel
 * master也可以有多个Selector，但是一个Selector对应一个PORT
 */
@SuppressWarnings("all")
public class ChatServer {

    public static void main(String[] args) throws Exception {
        // 配置一个线程
        EventLoopGroup masterGroup = new NioEventLoopGroup(1);
        // 每个线程都有一个Selector, thread - Selector - *clientChannel
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(masterGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            // 初始化
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    /*服务端处理器: 一系类回调*/
                    socketChannel.pipeline().addLast(new ChatServerHandler());
                }
            });
            System.out.println("netty server start。。");
            // 绑定一个端口并且同步, 生成了一个ChannelFuture异步对象
            // 启动服务器(并绑定端口)，bind是异步操作，sync方法是等待异步操作执行完毕
            ChannelFuture channelFuture = bootstrap.bind(9000).sync();
            // 判断事件执行情况
            System.out.println(channelFuture.isDone());
            // 给cf注册监听器，监听我们关心的事件
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("监听端口9000成功");
                    } else {

                        System.out.println("监听端口9000失败");
                    }
                }
            });
            // 对通道关闭进行监听，closeFuture是异步操作，监听通道关闭
            // 通过sync方法同步等待通道关闭处理完毕，这里会阻塞等待通道关闭完成
            // channelFuture.channel().closeFuture().sync();
            Channel channel = channelFuture.channel();
            ChannelFuture channelFuture1 = channel.closeFuture();
            channelFuture1.sync();
        } finally {
            masterGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
