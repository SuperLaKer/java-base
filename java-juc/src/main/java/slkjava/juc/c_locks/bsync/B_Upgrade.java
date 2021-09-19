package slkjava.juc.c_locks.bsync;

/**
 * synchronized关键字：
 * synchronized关键字会对修饰的代码加锁（同步访问），锁的类型：如下
 * <p>
 * 锁膨胀过程: 偏向锁(CAS) ---> 轻量级自旋锁 ---> mutex重量级锁
 * CAS修改对象头中的线程id，偏向锁。
 * 获取偏向锁的线程一般很快就会释放偏向锁(把lock对象保存的线程id置空)，这样其他线程可以通过CAS获取锁
 * <p>
 * 如果CAS失败，锁会自动升级为自旋锁
 * 自旋对象头存储指针执行线程私有栈，表示线程获取了自旋锁
 * <p>
 * 自旋失败膨胀为重量级锁
 * <p>
 * CAS和自旋线程不会进入阻塞状态，不会涉及上线文切换，性能比较高
 * 自旋有次数限制，所以不怎么浪费性能。自适应自旋，JVM自己动态优化
 * <p>
 * 重量级锁：操作系统底层的锁切换，涉及到内核线程的切换，开销比CAS和自旋大
 */
public class B_Upgrade {

}