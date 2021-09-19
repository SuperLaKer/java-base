package aa.my.javaio.tcp.threadmodel.model;

import aa.my.javaio.tcp.ByteUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class AcceptHandler extends Thread {

    private InputStream inputStream;
    private Socket serviceSocket;
    // 数据head长度：自定义协议
    private byte[] headByte = new byte[4];
    // 第一次读取数据（第一次读取数据有head）
    private boolean readFirst = true;
    // inputStream.available();
    private int availableLength = 0;
    private int totalDataLength = 0;
    private int totalDataLengthToUse = 0;
    // 数据拼接：第一次+第二次+···
    private byte[] byteArrayBuffer = new byte[0];

    public AcceptHandler(Socket serviceSocket) {
        this.serviceSocket = serviceSocket;
    }


    @Override
    public void run() {

        // 获取数据
        while (!isInterrupted()) {
            totalDataLength = getTotalDataLength();
            totalDataLengthToUse = totalDataLength;

            // 拼接数据
            while (totalDataLengthToUse > 0 && inputStream != null) {
                // 获取本次传输的总数据长度（前4个字节）
                // 结果比较
                if (totalDataLengthToUse <= 1000) {
                    readByteToArrayBuffer(totalDataLengthToUse);
                    readFirst = true;
                    break;
                } else {
                    readByteToArrayBuffer(1000);
                    totalDataLengthToUse -= 1000;
                    readFirst = false;
                }
            }

            if (byteArrayBuffer.length != 0) {
                if (byteArrayBuffer.length != totalDataLength) throw new RuntimeException("长度有损失");
                // 处理data
                String s = new String(byteArrayBuffer, StandardCharsets.UTF_8);
                System.out.println("收到：" + s);
                readFirst = true;
                byteArrayBuffer = new byte[0];
            }
        }
    }

    private void readByteToArrayBuffer(int byteLength) {
        byte[] bytes = new byte[byteLength];
        try {
            // 这里会阻塞
            int size = inputStream.read(bytes);
            readExceptionHandler(size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byteArrayBuffer = ArrayUtils.addAll(byteArrayBuffer, bytes);
    }

    private void readExceptionHandler(int b) {
        if (b == 0) throw new RuntimeException("inputStream.read异常");
    }


    /**
     * 获取本次传输总数据长度，防止粘包
     */
    private int getTotalDataLength() {

        System.out.println("getTotal。。。");
        // 不是第一次传输，直接处理availableLength;
        if (!readFirst) {
            try {
                return inputStream.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 第一次传输有 headByte，前4个字节
        try {
            inputStream = serviceSocket.getInputStream();
            if (inputStream == null) return 0;
            System.out.println("before readHead...");  // 阻塞到这里...
            int read = this.inputStream.read(headByte);
            if (read == 4) {
                return ByteUtils.byteArrayToInt(headByte);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return 0;
    }


    /**
     * 关闭资源
     */
    private void close(Closeable... args) {
        for (Closeable arg : args) {
            try {
                arg.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
