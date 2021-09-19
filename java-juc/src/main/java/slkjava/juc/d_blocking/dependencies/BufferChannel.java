package slkjava.juc.d_blocking.dependencies;

import java.util.concurrent.ArrayBlockingQueue;

public class BufferChannel<T> {
    ArrayBlockingQueue<T> blockingQueue;
    /**
     * full: 阻塞
     */
    public void put(T e) throws InterruptedException {
        blockingQueue.put(e);
    }

    public T take() throws InterruptedException {
        return blockingQueue.take();
    }

    // 创建ReentrantLock(fair)
    public BufferChannel(int channelSize) {
        this.blockingQueue = new ArrayBlockingQueue<>(channelSize);
    }
}
