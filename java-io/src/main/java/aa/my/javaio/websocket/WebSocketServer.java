package aa.my.javaio.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket服务端
 * <p>
 * websocket分为服务端和客户端
 * 客户端:html5
 */
@ServerEndpoint("/ws")
public class WebSocketServer {

    Session session;
    Integer onlineCount = 0;
    CopyOnWriteArraySet<WebSocketServer> clientList = new CopyOnWriteArraySet<>();

    /**
     * 与客户端建立连接会调用此方法
     *
     * @param session s
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getBasicRemote()+": websocket客户端已连接...");
        this.session = session;
        this.clientList.add(this);
        this.onlineCount += 1;
    }

    @OnClose
    public void onClose() {
        clientList.remove(this);
        if (this.onlineCount > 0) {
            this.onlineCount -= 1;
        }

        System.out.printf("当前在线人数: %d", this.onlineCount);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        System.out.printf("接收到消息: %s", message);
        // 群发
        for (WebSocketServer webSocketServer : this.clientList) {
            webSocketServer.sendMessage(message);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.printf("发送异常: %s", error.getMessage());
    }

    public void sendMessage(String message) throws Exception {
        this.session.getBasicRemote().sendText(message);
    }
}
