package slkjava.other.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionWait implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }


    @Override
    public void run() {
        lock.lock();  // 尝试获取锁
        try {
            System.out.println("conditionWait开始执行。。。");
            condition.await();  // 阻塞：主动让出时间片
            System.out.println("conditionWait执行完成。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
