package aa.my.javaio.netty.b_chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group); //设置线程组
            bootstrap.channel(NioSocketChannel.class); // 使用 NioSocketChannel 作为客户端的通道实现
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    //加入处理器
                    channel.pipeline().addLast(new ChatClientHandler());
                }
            });
            System.out.println("netty client start");
            //启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            //对关闭通道进行监听
            Channel channel = channelFuture.channel();

            // sendTo(channel);  // 容易粘包、拆包
            // 无粘包
            inputAndSend(channel);
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 和TCP协议有关
     * 有可能收到的数据：hello world!(正常), hello(拆包), world!hello world!(粘包)
     */
    private static void sendTo(Channel channel) {
        for (int i = 0; i < 20; i++) {
            channel.writeAndFlush("hello world!");
        }
    }

    /**
     * 不容易造成粘包、拆包的现象
     */
    private static void inputAndSend(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String msg = scanner.nextLine();
            channel.writeAndFlush(msg);
        }
    }
}
