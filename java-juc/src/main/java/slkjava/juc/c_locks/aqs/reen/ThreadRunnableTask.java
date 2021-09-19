package slkjava.juc.c_locks.aqs.reen;

import java.util.concurrent.CountDownLatch;

public class ThreadRunnableTask implements Runnable {

    CountDownLatch countDownLatch;
    MethodWithLock methodWithLock;

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public ThreadRunnableTask(MethodWithLock methodWithLock) {
        this.methodWithLock = methodWithLock;
    }

    @Override
    public void run() {
        methodWithLock.doDemo();
        countDownLatch.countDown();
    }

}
