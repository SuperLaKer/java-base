package slkjava.juc.d_blocking.array;

import slkjava.juc.d_blocking.dependencies.SlkRandom;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue
 * 数组结构不可扩容
 * 获取锁、队列没满、put元素、释放锁
 * queue.put(SlkRandom.str(6));
 * 获取锁、队列满了、添加到条件队列、挂起线程
 * Adds a new waiter to wait queue.
 * 添加到等待队列，挂起线程
 */
public class AArrayQueue {
    public static void main(String[] args) throws Exception {
        consumerThreads(StaticQueue.queue);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("main...");

        new Thread(new TheConsumer(StaticQueue.queue)).start();

        TimeUnit.SECONDS.sleep(500);
    }

    /**
     * 阻塞，查看条件阻塞队列
     */
    private static void consumerThreads(ArrayBlockingQueue<String> queue) throws InterruptedException {
        Thread thread0 = new Thread(new TheProducer(queue));
        thread0.setName("thread-0");
        thread0.start();

        Thread thread1 = new Thread(new TheProducer(queue));
        thread1.setName("thread-1");
        thread1.start();
        Thread thread2 = new Thread(new TheProducer(queue));
        thread2.setName("thread-2");
        thread2.start();

        TimeUnit.SECONDS.sleep(2);
        Thread thread = new Thread(new TheProducer(queue));
        thread.setName("thread3");
        thread.start();
    }
}

class TheConsumer implements Runnable {
    ArrayBlockingQueue<String> queue;

    public TheConsumer(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true){
                System.out.println(queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TheProducer implements Runnable {
    ArrayBlockingQueue<String> queue;

    public TheProducer(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(SlkRandom.str(6));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}