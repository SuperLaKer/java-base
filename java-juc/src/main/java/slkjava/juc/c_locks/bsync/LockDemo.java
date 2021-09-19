package slkjava.juc.c_locks.bsync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static ReentrantLock lock = new ReentrantLock(true);
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        TaskClass taskClass = new TaskClass();

        for (int i = 1; i <= 2; i++) {
            Thread thread = new Thread(taskClass);
            thread.setName("thread_" + i);
            thread.start();
            TimeUnit.MILLISECONDS.sleep(20);
        }
        countDownLatch.await();
        System.out.println(TaskClass.num);

    }

    static class TaskClass implements Runnable {
        static int num = 0;

        @Override
        public void run() {
            // 中间线程观察lock()方法
            lock.lock();
            num++;
            // 第一个线程阻塞
            lock.unlock();
            countDownLatch.countDown();
        }
    }
}
