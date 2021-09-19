package aa.my.javaio.netty.d_gt1024.handlers;

import aa.my.javaio.tcp.ByteUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class ServerEventHandler extends ChannelInboundHandlerAdapter {

    boolean lastFinish = true;
    byte[] byteArrayBuffer = new byte[0];
    int totalDataLength = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] msg1 = (byte[]) msg;
        System.out.println(new String(msg1, StandardCharsets.UTF_8));
    }

    private boolean getTotalDataLength(byte[] bytes) {
        byte[] dataLengthBytes = new byte[4];
        System.arraycopy(bytes, 0, dataLengthBytes, 0, 3);
        totalDataLength = ByteUtils.byteArrayToInt(dataLengthBytes);
        return totalDataLength > 0;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // String address = ctx.channel().remoteAddress().toString();
        // ByteBuf buf = Unpooled.copiedBuffer(address+": ", CharsetUtil.UTF_8);
        ctx.writeAndFlush("msg from server complete。。。");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
