package aa.my.javaio.io;


/**
 * selector监听事件 read write
 * channel一一映射epoll
 * <p>
 * bio 普通的socket：socket.accept(), socket.getInputStream() 均会阻塞，影响服务端性能
 * <p>
 * nio 轮询机制，大大提高了服务端连接数量. 通过不断改变channel的类型，确定下次如何这个channel（accept or read or write）
 * nio 同步：对于clientChannel，消息到了只能等待selector轮询
 */
public class IOMain {
}
