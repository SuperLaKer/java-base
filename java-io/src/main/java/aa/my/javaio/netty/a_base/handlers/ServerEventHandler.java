package aa.my.javaio.netty.a_base.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerEventHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象, 含有通道channel，管道pipeline
     * @param msg 就是客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ByteBuf buf = (ByteBuf) msg;
        // String stringMsg = buf.toString(CharsetUtil.UTF_8);
        // System.out.println(stringMsg);
        System.out.println("server: "+ msg);
        ctx.writeAndFlush("msg from server");
    }

    /**
     * 数据读取完毕处理方法
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // String address = ctx.channel().remoteAddress().toString();
        // ByteBuf buf = Unpooled.copiedBuffer(address+": ", CharsetUtil.UTF_8);
        // ctx.writeAndFlush(buf);
        ctx.writeAndFlush("msg from server complete。。。");
    }

    /**
     * 处理异常, 一般是需要关闭通道
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
