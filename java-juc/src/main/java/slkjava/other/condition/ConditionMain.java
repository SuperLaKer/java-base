package slkjava.other.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC中的 等待唤醒机制
 */
public class ConditionMain {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // 获取锁，主动让出
        new Thread(new ConditionWait(lock, condition)).start();
        // 获取锁，唤醒，执行完成。（先执行完，再唤醒其他阻塞线程）
        new Thread(new ConditionNotify(lock, condition)).start();
    }
}
