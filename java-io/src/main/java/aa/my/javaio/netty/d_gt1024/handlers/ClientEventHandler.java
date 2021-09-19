package aa.my.javaio.netty.d_gt1024.handlers;

import aa.my.javaio.tcp.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.charset.StandardCharsets;


public class ClientEventHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int j = 0; j < 10; j++) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 52; i++) {
                stringBuffer.append("222_from_client____!");
            }

            byte[] bytes = ByteUtils.intToByteArray(stringBuffer.length());
            byte[] bytes1 = ArrayUtils.addAll(bytes, new String(stringBuffer).getBytes(StandardCharsets.UTF_8));
            ByteBuf buf2 = Unpooled.copiedBuffer(bytes1);
            ctx.writeAndFlush(buf2);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ByteBuf buf = (ByteBuf) msg;
        // ByteBuf buf2 = Unpooled.copiedBuffer(nextLine, CharsetUtil.UTF_8);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
