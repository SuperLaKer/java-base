package aa.my.javaio.tcp.simple;

import aa.my.javaio.tcp.ByteUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ServerSocket socket = null;
        Socket serviceSocket = null;
        InputStream inputStream = null;

        byte[] byteArrayBuffer = new byte[0];

        // 第一次接收的数据带有头
        boolean readFirst = true;
        int totalLength = 0;
        int dataLength = 0;

        try {
            socket = new ServerSocket(6666);
            serviceSocket = socket.accept();
            for (int i = 0; i < 10000; i++) {
                inputStream = serviceSocket.getInputStream();
                // 是否有数据
                int available = inputStream.available();
                // 有数据
                if (available > 0) {
                    // 读取前4个字节
                    byte[] headByte = new byte[4];
                    if (readFirst) {
                        inputStream.read(headByte);
                        totalLength = ByteUtils.byteArrayToInt(headByte);
                        dataLength = ByteUtils.byteArrayToInt(headByte);
                    }

                    // 读取data
                    while (dataLength > 0) {
                        if (dataLength <= 1000) {
                            byte[] bytes = new byte[dataLength];
                            int size = inputStream.read(bytes);
                            byteArrayBuffer = ArrayUtils.addAll(byteArrayBuffer, bytes);
                            readFirst = true;
                            break;
                        } else {
                            byte[] bytes = new byte[1000];
                            inputStream.read(bytes);
                            byteArrayBuffer = ArrayUtils.addAll(byteArrayBuffer, bytes);
                            dataLength -= 1000;
                            readFirst = false;
                        }
                    }
                    if (byteArrayBuffer.length != 0) {
                        // 处理data
                        if (byteArrayBuffer.length != totalLength) {
                            throw new RuntimeException("数据读取错误");
                        }
                        String s = new String(byteArrayBuffer, StandardCharsets.UTF_8);
                        if ("close".equals(s)) close(serviceSocket, inputStream);
                        System.out.println("收到：" + s);
                        readFirst = true;
                        byteArrayBuffer = new byte[0];
                    }
                }
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (Exception e) {
            readFirst = true;
            e.printStackTrace();
        } finally {
            readFirst = true;
            if (serviceSocket != null) serviceSocket.close();
            if (inputStream != null) inputStream.close();
            if (socket != null) socket.close();
        }
    }

    private static void close(Closeable socket, Closeable closeable) throws IOException {
        socket.close();
        closeable.close();
    }
}
