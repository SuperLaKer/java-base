package aa.my.javaio.tcp.simple;

import aa.my.javaio.tcp.ByteUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.setKeepAlive(true);
        socket.connect(new InetSocketAddress("127.0.0.1", 6666));

        StringBuffer stringBuffer = new StringBuffer();

        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 1000; i++) {
            Scanner scanner = new Scanner(System.in);
            int i1 = scanner.nextInt();
            if (i1 == 88) close(socket, outputStream);
            for (int j = 0; j < i1; j++) {
                stringBuffer.append("你你你s你你你s你你你s你你你s你你你s你你你s你你你s你你你s你你你s你你你s");
            }
            // 数据长度问题

            // 数据
            String s = new String(stringBuffer);
            byte[] dataBytes = s.getBytes(StandardCharsets.UTF_8);
            int dataLength = dataBytes.length;
            // 自定义头: 前4个字节
            byte[] headBytes = ByteUtils.intToByteArray(dataLength);
            // 头+数据
            byte[] transData = ArrayUtils.addAll(headBytes, dataBytes);

            outputStream.write(transData);
            stringBuffer.delete(0, stringBuffer.length());
            outputStream.flush();

        }
    }

    private static void close(Socket socket, OutputStream outputStream) throws IOException {
        outputStream.write("close".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        socket.close();
        outputStream.close();
    }
}
