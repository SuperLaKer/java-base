package aa.my.javaio.io.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class AIOServer {
    public static void main(String[] args) throws Exception {
        final AsynchronousServerSocketChannel serverChannel =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9000));

        serverChannel.accept(null, new CallbackAccept(serverChannel));

    }
}


