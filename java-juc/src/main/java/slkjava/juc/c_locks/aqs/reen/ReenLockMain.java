package slkjava.juc.c_locks.aqs.reen;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import slkjava.juc.BeanUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Configuration
@ComponentScan
@Import({BeanUtils.class})
public class ReenLockMain {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(ReenLockMain.class);
        ac.refresh();
        try {
            testReenLock(ac);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ac.close();
        }
    }

    private static void testReenLock(AnnotationConfigApplicationContext ac) throws Exception {

        MethodWithLock methodWithLock = new MethodWithLock(new ReentrantLock());
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        ThreadPoolTaskExecutor poolTaskExecutor = ac.getBean(ThreadPoolTaskExecutor.class);
        ThreadRunnableTask threadRunnableTask = new ThreadRunnableTask(methodWithLock);
        threadRunnableTask.setCountDownLatch(countDownLatch);
        for (int i = 0; i < 1000; i++) {
            // 多个线程执行同一份代码
            poolTaskExecutor.submit(threadRunnableTask);
        }
        poolTaskExecutor.shutdown();
        countDownLatch.await(3, TimeUnit.SECONDS);
        System.out.println(methodWithLock.num);
    }
}
