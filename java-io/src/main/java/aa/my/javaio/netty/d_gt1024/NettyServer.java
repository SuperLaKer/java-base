package aa.my.javaio.netty.d_gt1024;


import aa.my.javaio.netty.d_gt1024.channelInits.ServerChannelInit;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * 两个线程池默认线程数：CPU_CORE * 2, 这里指定为2
 * worker每个线程都有一个Selector, 每个线程对应多个clientChannel
 * master也可以有多个Selector，但是一个Selector对应一个PORT
 */
@SuppressWarnings("all")
public class NettyServer implements Runnable {

    // 构造
    CountDownLatch countDownLatch;

    public static void  start(CountDownLatch countDownLatch) throws Exception {
        Thread thread = new Thread(new NettyServer(countDownLatch));
        thread.start();
    }

    @SneakyThrows
    @Override
    public void run() {
        // 配置一个线程
        EventLoopGroup masterGroup = new NioEventLoopGroup(1);
        // 每个线程都有一个Selector, thread - Selector - *clientChannel
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(masterGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);

            // 初始化：channel -> channelPipline -> channelHandler
            bootstrap.childHandler(new ServerChannelInit());
            // 同步等待获取绑定后的channel(未来的channel)
            ChannelFuture channelFuture = bootstrap.bind(9000).sync();
            // 给channel添加一个回调
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("监听端口9000成功");
                        countDownLatch.countDown();
                    } else {
                        System.out.println("监听端口9000失败");
                    }
                }
            });
            // 同步等待关闭事件，也就是一直等直到调用关闭事件
            channelFuture.channel().closeFuture().sync();
        } finally {
            masterGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public NettyServer(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public NettyServer() {
    }

}
