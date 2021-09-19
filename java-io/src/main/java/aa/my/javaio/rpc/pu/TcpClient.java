package aa.my.javaio.rpc.pu;


import aa.my.javaio.rpc.serializable.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Java原生序列化：ObjectOutputStream
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8081);
        // 原生outputStream只能写byte[]
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream enhanceOutPutStream = new ObjectOutputStream(outputStream);
        // 必须实现Serializable
        User user = new User("ming", new Date(System.currentTimeMillis()),
                new Time(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        enhanceOutPutStream.writeObject(user);
        socket.close();
    }
}
// 序列化
