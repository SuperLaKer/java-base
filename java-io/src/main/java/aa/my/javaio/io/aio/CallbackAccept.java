package aa.my.javaio.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class CallbackAccept implements CompletionHandler<AsynchronousSocketChannel, Object> {

    AsynchronousServerSocketChannel serverChannel;

    public CallbackAccept(AsynchronousServerSocketChannel serverChannel) {
        this.serverChannel = serverChannel;
    }

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
        try {
            // 必须再次accept
            serverChannel.accept(attachment, this);
            System.out.println(socketChannel.getRemoteAddress());
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer, buffer, new CallbackRead(socketChannel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace();
    }
}
