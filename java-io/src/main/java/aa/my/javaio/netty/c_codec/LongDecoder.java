package aa.my.javaio.netty.c_codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 1    8     16    32   64
 * bit、byte、short、int、long、double、float、char、boolean
 */
public class LongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        // 1long = 8byte
        if (buf.readableBytes() >= 8) {
            // out相当于next
            out.add(buf.readLong());
        }
    }
}
