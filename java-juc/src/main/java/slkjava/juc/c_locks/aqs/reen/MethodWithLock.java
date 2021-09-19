package slkjava.juc.c_locks.aqs.reen;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;


@Component
@Data
public class MethodWithLock {

    public static Integer staticNum = 0;
    public Integer num = 0;

    ReentrantLock lock;
    static ReentrantLock staticLock = new ReentrantLock();

    public MethodWithLock() {
    }

    // 定义一个唯一的锁，多个实例使用同一个锁
    public MethodWithLock(ReentrantLock lock) {
        this.lock = lock;
    }

    public void doDemo() {
        lock.lock();
        num += 1;
        lock.unlock();
    }

    public static synchronized void doStatic() {
        try {
            if (staticLock.tryLock()) {
                staticLock.lock();
                staticNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            staticLock.unlock();
        }
    }


}
