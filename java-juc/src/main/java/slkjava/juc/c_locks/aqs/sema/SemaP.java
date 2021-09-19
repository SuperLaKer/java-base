package slkjava.juc.c_locks.aqs.sema;

import java.util.concurrent.Semaphore;

public class SemaP {

    public static void main(String[] args) throws InterruptedException {

        // state == 5
        Semaphore semaphore = new Semaphore(5);

        // 加锁，remaining = state - 1
        // 如果 remaining > 0; acquire()执行完成，进入主逻辑

        // 如果remaining < 0; 新建node,直接tryLock,如果成功返回acquire()成功
        semaphore.acquire(1);
        semaphore.release();
    }
}
