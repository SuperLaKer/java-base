package aa.slkjava.thread.pools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExPoolApplication {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        TaskRunnable taskRunnable = new TaskRunnable(countDownLatch);

        for (int i = 0; i < 6; i++) {
            threadPool.execute(taskRunnable);
        }
        for (int i = 0; i < 5; i++) {
            countDownLatch.countDown();
        }
        threadPool.shutdown();
    }
}
