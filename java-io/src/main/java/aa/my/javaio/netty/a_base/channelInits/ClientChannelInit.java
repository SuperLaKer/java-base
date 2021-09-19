package aa.my.javaio.netty.a_base.channelInits;

import aa.my.javaio.netty.a_base.handlers.ClientEventHandler;
import aa.my.javaio.netty.c_codec.StringDecoder;
import aa.my.javaio.netty.c_codec.StringEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new ClientEventHandler());
    }
}
