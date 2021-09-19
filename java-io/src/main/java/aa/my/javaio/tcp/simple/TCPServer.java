package aa.my.javaio.tcp.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {

    private final int port;
    private boolean isFinished;
    private ServerSocket serverSocket;
    private final ArrayList<SocketThread> socketThreads;


    public TCPServer(int port) {
        this.port = port;
        socketThreads = new ArrayList<>();
    }


    public void start() {
        isFinished = false;
        try {
            // 创建服务器套接字，绑定到指定的端口
            serverSocket = new ServerSocket(port);
            // 等待客户端连接
            while (!isFinished) {
                Socket socket = serverSocket.accept();//接受连接
                // 创建线程处理连接
                SocketThread socketThread = new SocketThread(socket);
                socketThreads.add(socketThread);
                socketThread.start();
            }
        } catch (IOException e) {
            isFinished = true;
        }
    }


    public void stop() {
        isFinished = true;
        for (SocketThread socketThread : socketThreads) {
            socketThread.interrupt();
            socketThread.close();
        }
        try {
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SocketThread extends Thread {

        private final Socket socket;
        private InputStream in;
        private OutputStream out;

        SocketThread(Socket socket) {
            this.socket = socket;
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                if (in == null) {
                    return;
                }
                try {
                    int available = in.available();
                    if (available > 0) {
                        byte[] buffer = new byte[available];
                        int size = in.read(buffer);
                        if (size > 0) {
                            String data = new String(buffer, 0, size);
                            System.out.println("TCPClient say :" + data);
                            //返回结果给TcpClient
                            String response = "TCPServer response:" + data;
                            out.write(response.getBytes());
                            out.flush();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        void close() {

            try {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}