package aa.my.javaio.netty.d_gt1024.gtCodec;

import aa.my.javaio.tcp.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public class BigDataDecoder extends ByteToMessageDecoder {
    int requestFullDataLength = 0;
    int requestFullDataLengthToComputed = 0;
    // 前一次请求是否处理完成
    boolean preRequestIsFinish = true;
    byte[] byteArrayBuffer = new byte[0];

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {

        getSingleRequestDataLength(buf);

        // 多次请求压缩，粘包
        if (requestFullDataLengthToComputed <= buf.readableBytes()) {
            byte[] bytes = new byte[requestFullDataLengthToComputed];
            buf.readBytes(bytes);
            // 如果是true不用缓存 todo
            byteArrayBuffer = ArrayUtils.addAll(byteArrayBuffer, bytes);
            if (byteArrayBuffer.length != requestFullDataLength) {
                throw new RuntimeException("数据混乱，系统异常，信息发送失败");
            }
            out.add(byteArrayBuffer);
            byteArrayBuffer = new byte[0];
            // 重新解析头部
            preRequestIsFinish = true;
        } else {
            // 不用担心前四位头的问题, buf的index会表变化
            // 拆包了：先把这一部分数据缓存到一个buff中
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            byteArrayBuffer = ArrayUtils.addAll(byteArrayBuffer, bytes);
            requestFullDataLengthToComputed -= bytes.length;
            preRequestIsFinish = false;
        }
    }


    /**
     * 第一次访问：获取头部信息，头部信息位数据总长度
     * 单次请求处理完成（拆包、信息合并等），系统会往buf中填充下次请求发送的数据
     * 所以不会存在"headByte"分裂的情况。
     * 就算中间某次请求的"headByte"拆包了，等前面的数据取走了"headByte"又完整了
     */
    private void getSingleRequestDataLength(ByteBuf buf) {
        // 开始处理新的请求
        if (preRequestIsFinish) {
            // todo 4位直接readInt
            byte[] headByte = new byte[4];
            buf.readBytes(headByte);
            requestFullDataLength = ByteUtils.byteArrayToInt(headByte);
            requestFullDataLengthToComputed = requestFullDataLength;
        }
    }
}
