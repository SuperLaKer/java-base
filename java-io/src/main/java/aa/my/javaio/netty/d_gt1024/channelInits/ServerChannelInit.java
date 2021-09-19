package aa.my.javaio.netty.d_gt1024.channelInits;

import aa.my.javaio.netty.d_gt1024.gtCodec.BigDataDecoder;
import aa.my.javaio.netty.d_gt1024.handlers.ServerEventHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 先解码然后发送给handler
        pipeline.addLast(new BigDataDecoder());
        pipeline.addLast(new ServerEventHandler());
    }
}
