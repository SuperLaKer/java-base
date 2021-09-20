package slkjava.tools.semaphore;


import java.util.concurrent.Semaphore;

/**
 * 控制资源并发访问量
 */
public class SemaphoreMain {
    public static void main(String[] args) throws InterruptedException {
        // 允许5个线程访问资源
        Semaphore semaphore = new Semaphore(5);
        semaphore.acquire();
        // 资源run方法
        semaphore.release();
    }
}
