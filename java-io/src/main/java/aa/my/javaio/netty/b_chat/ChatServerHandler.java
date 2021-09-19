package aa.my.javaio.netty.b_chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.StandardCharsets;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 客户端建立连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
        channels.writeAndFlush(ctx.channel().remoteAddress() + "->login");
    }

    // 客户端关闭连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "->下线了");
    }

    // read事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString(StandardCharsets.UTF_8));
    }


    // write事件
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = Unpooled.copiedBuffer("HelloClient", CharsetUtil.UTF_8);
        // 给客户端发送数据
        ctx.writeAndFlush(buf);
    }
}
