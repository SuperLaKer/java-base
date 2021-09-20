package slkjava.tools.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 主   countDownLatch.await();
 * 子们 countDownLatch.countDown()
 */
public class CountDownMain {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // 新建几个子线程（类继承Runnable），把countDownLatch传递进去

        // 可以设置等待时间
        countDownLatch.await();
    }
}
