package aa.my.javaio.netty.c_codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class StringDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        int bytesLength = buf.readableBytes();

        // 1char = 1byte
        if (bytesLength >= 1) {
            // out相当于next
            out.add(buf.readCharSequence(bytesLength, StandardCharsets.UTF_8));
        }
    }
}
