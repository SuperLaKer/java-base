package aa.slkjava.thread.pools.executors;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors静态类 创建几种常用的线程池Executor实例
 */
public class ExecutorsMain {
    public static void main(String[] args) {
        // ExecutorService extends Executor
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    }
}
