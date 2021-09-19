package aa.my.javaio.netty.a_base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 启动server和client
 */
public class ServerAndClientBootApplication {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        NettyServer.start(countDownLatch);
        countDownLatch.await(1, TimeUnit.SECONDS);
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
    }

}
