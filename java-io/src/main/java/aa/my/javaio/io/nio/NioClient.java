package aa.my.javaio.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


public class NioClient {
    private Selector selector;

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
        client.initClient("127.0.0.1", 9000);
        client.connect();
    }

    public void initClient(String ip, int port) throws IOException {
        SocketChannel clientBufferChannel = SocketChannel.open();
        clientBufferChannel.configureBlocking(false);
        this.selector = Selector.open();
        clientBufferChannel.connect(new InetSocketAddress(ip, port));
        clientBufferChannel.register(selector, SelectionKey.OP_CONNECT);
    }


    public void connect() throws IOException {
        // 轮询
        while (true) {
            // 阻塞
            selector.select();
            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();

                /*连接事件*/
                if (key.isConnectable()) {
                    SocketChannel clientBufferChannel = (SocketChannel) key.channel();
                    // 如果正在连接，则完成连接
                    if (clientBufferChannel.isConnectionPending()) {
                        clientBufferChannel.finishConnect();
                    }
                    // 设置成非阻塞
                    clientBufferChannel.configureBlocking(false);
                    /*给服务端发送信息*/
                    ByteBuffer buffer = ByteBuffer.wrap("HelloServer".getBytes());
                    clientBufferChannel.write(buffer);
                    // 注册读取事件
                    clientBufferChannel.register(this.selector, SelectionKey.OP_READ); // 获得了可读的事件

                    /*读事件*/
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }


    /*读取信息*/
    public void read(SelectionKey key) throws IOException {
        SocketChannel clientBufferChannel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(512);
        int len = clientBufferChannel.read(buffer);
        if (len != -1) {
            System.out.println("客户端收到信息：" + new String(buffer.array(), 0, len));
        }
    }
}