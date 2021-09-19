package aa.my.javaio.netty.a_base.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;


/**
 * 客户端：socket.connect()，OutputStream：write()，flush(), InputStream.read()
 * 服务端：socket.accept()，InputStream.read(), OutputStream：write()，flush()。
 *
 * 服务端事件：accept(), read()。write之后重置事件为read()
 */
public class ClientEventHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接服务器成功
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // byte[] bytes = "!!哈哈!!".getBytes(StandardCharsets.UTF_8);
        // ByteBuf buf2 = Unpooled.copiedBuffer(bytes);
        // System.out.println("client: 发送信息");
        // ctx.writeAndFlush(buf2);
        for (int i = 0; i < 200; i++) {
            ctx.writeAndFlush("msg_from_client____!");
        }
    }

    /**
     * 当通道有读取事件时会触发，即服务端发送数据给客户端
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ByteBuf buf = (ByteBuf) msg;
        // System.out.println("收到服务端的消息:" + buf.toString(CharsetUtil.UTF_8));
        // System.out.println("服务端的地址： " + ctx.channel().remoteAddress());
        // Scanner scanner = new Scanner(System.in);
        // String nextLine = scanner.nextLine();
        // ByteBuf buf2 = Unpooled.copiedBuffer(nextLine, CharsetUtil.UTF_8);
        // ctx.writeAndFlush(buf2);

        System.out.println("client: "+msg);
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        ctx.writeAndFlush(nextLine);
    }

    /**
     * 异常处理方法
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
