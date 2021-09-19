package slkjava.other.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionNotify implements Runnable {

    private Lock lock;
    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }


    @Override
    public void run() {
        lock.lock();  // 尝试获取锁
        try {
            System.out.println("conditionNotify开始执行。。。");
            condition.signal();  // 阻塞：主动让出时间片
            System.out.println("conditionNotify执行完成。。。");
        } finally {
            lock.unlock();
        }
    }
}
