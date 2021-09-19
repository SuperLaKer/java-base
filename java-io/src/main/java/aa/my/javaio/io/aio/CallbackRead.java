package aa.my.javaio.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class CallbackRead implements CompletionHandler<Integer, ByteBuffer> {

    AsynchronousSocketChannel socketChannel;

    public CallbackRead(AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        System.out.println(new String(buffer.array(), 0, result));
        socketChannel.write(ByteBuffer.wrap("HelloClient".getBytes()));
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        exc.printStackTrace();
    }
}
