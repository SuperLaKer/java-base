package slkjava.juc.c_locks.bsync;

import org.springframework.stereotype.Component;


/**
 * synchronized特性：显示锁、互斥锁、重入锁、重量级锁，遇到锁立即阻塞
 * 因synchronized被阻塞的线程会涉及到上下文切换（保存寄存器现场到PCB空间）
 * <p>
 * <p>
 * static把锁加到类对象上，所有访问资源的线程"串行化执行"
 * 没有static把锁添加到实例对象上，多个实例表示有多把锁
 * 托管给spring，bean需要时单例的
 * UnsafeUtils.getUnsafe().monitorEnter();
 */
@Component
public class A_lock {

    public static Integer staticNum = 0;
    public volatile static Integer staticVolatileNum = 0;
    public Integer num;
    public volatile Integer volatileNum;

    /**
     * 全局锁，因为锁的对象是B_synchronized，它存放在"元空间"只有一份。
     * 所有调用此方法的线程都会"串行化"执行该方法
     */
    public static synchronized void doStatic() {
        staticNum++;
    }

    /**
     * 锁对象：B_synchronized的一个实例，多个线程同时使用该实例的时候，这些线程会"串行化"执行该方法
     */
    public synchronized void doDemo() {
        num++;
    }

    /**
     * 锁对象：B_synchronized的一个实例。该实例会作为参数传递给多个线程
     * 如果又创建一个新的对象 new B_()。这两个对象代表两个不同的锁
     */
    public synchronized void doDemo1() {
        synchronized (this) {
            num++;
        }
    }


}
