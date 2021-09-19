package aa.my.javaio.tcp.threadmodel;

import aa.my.javaio.tcp.threadmodel.model.MultiThreadServer;
import aa.my.javaio.tcp.threadmodel.model.SimpleClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
@ComponentScan(basePackages = {"aa.my.javaio.tcp.threadmodel.model"})
public class ThreadModelApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(ThreadModelApplication.class);
        ac.refresh();

        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            MultiThreadServer multiThreadServer = ac.getBean(MultiThreadServer.class);
            SimpleClient simpleClient = ac.getBean(SimpleClient.class);

            multiThreadServer.setCountDownLatch(countDownLatch);
            simpleClient.setCountDownLatch(countDownLatch);
            Thread serverThread = new Thread(multiThreadServer);

            serverThread.start();
            simpleClient.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ac.close();
        }
    }
}
