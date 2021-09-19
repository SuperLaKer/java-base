package slkjava.juc.d_blocking.array;

import java.util.concurrent.ArrayBlockingQueue;

public class StaticQueue {

    static ArrayBlockingQueue<String> queue;

    static {
        queue = new ArrayBlockingQueue<String>(1);
    }
}
