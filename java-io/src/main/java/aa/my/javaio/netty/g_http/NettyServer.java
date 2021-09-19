package aa.my.javaio.netty.g_http;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;

/**
 *
 */
@SuppressWarnings("all")
public class NettyServer {

    @SneakyThrows
    public static void main(String[] args) {
        // 配置一个线程
        EventLoopGroup masterGroup = new NioEventLoopGroup(1);
        // 每个线程都有一个Selector, thread - Selector - *clientChannel
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(masterGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);

            bootstrap.childHandler(new ServerChannelInit());
            ChannelFuture channelFuture = bootstrap.bind(9000).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("监听端口9000成功");
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
}
