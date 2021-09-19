package aa.my.javaio.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * nio核心组件：
 * nio通过channel完成数据收发。（golang：缓冲channel,直接new. 写入ch <- data ， 等待读取data <- ch）
 * <p>
 * channel可以完成生产消费模型，肯定是双向数据通信
 * client - buffer-Channel - selector - Threads - Server
 */
public class NIOServer {
    //public static ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * channelSocket与selector绑定
     */
    public static void main(String[] args) throws IOException {

        // socketChannel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(9000));
        // selector
        Selector selector = Selector.open();
        // serverChannel与selector绑定，并注册accept事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮询selector, 等待客户端连接(accept事件)
        while (true) {
            // 监听channels，缓存部分channel，统一处理
            // 如果channels没有事件阻塞
            int select = selector.select();
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                // 已经处理
                it.remove();
                handle(key);
            }
        }
    }

    /**
     * accept事件：新的客户端来了
     * read事件：客户端发送了请求
     * write事件：服务器处理完成，需要响应数据
     */
    private static void handle(SelectionKey key) throws IOException {

        // accept事件
        if (key.isAcceptable()) {
            // 建立连接
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel clientBufferChannel = serverChannel.accept();
            clientBufferChannel.configureBlocking(false);
            // 绑定selector, 注册read事件等待客户端发送数据
            clientBufferChannel.register(key.selector(), SelectionKey.OP_READ);

            // read事件
        } else if (key.isReadable()) {
            SocketChannel clientBufferChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // read方法不会阻塞
            int len = clientBufferChannel.read(buffer);
            if (len != -1) {
                System.out.println("读取到客户端发送的数据：" + new String(buffer.array(), 0, len));
            }
            ByteBuffer bufferToWrite = ByteBuffer.wrap("HelloClient".getBytes());
            clientBufferChannel.write(bufferToWrite);

            // 注册write事件，等待服务端响应
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            // write事件
        } else if (key.isWritable()) {
            SocketChannel clientBufferChannel = (SocketChannel) key.channel();
            System.out.println("write事件");
            // NIO事件触发是水平触发

            /*事件二选一*/
            // 使用Java的NIO编程的时候，在没有数据可以往外写的时候要取消写事件，
            // 在有数据往外写的时候再注册写事件
            //key.interestOps(SelectionKey.OP_READ);
            clientBufferChannel.close();
        }
    }
}