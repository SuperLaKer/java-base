package aa.slkjava.thread.pools;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

public class TaskRunnable implements Runnable{

    CountDownLatch countDownLatch;

    public TaskRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {
        countDownLatch.await();
        System.out.println("xxx");
    }
}
