package aa.my.javaio.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socketServer
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            // 阻塞，等待连接
            Socket socket = serverSocket.accept();
            // 一个连接对应一个线程
            new Thread(() -> {
                try {
                    handler(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void handler(Socket socket) throws IOException {

        System.out.println("thread id = " + Thread.currentThread().getId());


        byte[] bytes = new byte[1024];
        // 没有数据就阻塞
        int read = socket.getInputStream().read(bytes);
        // 有数据
        if (read != -1) {
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
            System.out.println("thread id = " + Thread.currentThread().getId());
        }
        socket.getOutputStream().write("HelloClient".getBytes());
        socket.getOutputStream().flush();
    }
}