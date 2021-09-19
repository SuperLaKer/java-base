package aa.my.javaio.tcp.threadmodel.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.CountDownLatch;

@Component
public class MultiThreadServer  implements Runnable, ApplicationContextAware {
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private CountDownLatch countDownLatch;

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            Socket serviceSocket = null;
            try {
                serviceSocket = socket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 多线程接收请求
            try {
                serviceSocket.setKeepAlive(true);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            threadPoolTaskExecutor.execute(new AcceptHandler(serviceSocket));
            countDownLatch.countDown();
            System.out.println("连接成功: " + serviceSocket.getPort());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.threadPoolTaskExecutor = applicationContext.getBean(ThreadPoolTaskExecutor.class);
    }
}