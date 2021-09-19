package aa.my.javaio.netty.a_base.channelInits;

import aa.my.javaio.netty.a_base.handlers.ServerEventHandler;
import aa.my.javaio.netty.c_codec.StringDecoder;
import aa.my.javaio.netty.c_codec.StringEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 先解码然后发送给handler
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new ServerEventHandler());
    }
}
