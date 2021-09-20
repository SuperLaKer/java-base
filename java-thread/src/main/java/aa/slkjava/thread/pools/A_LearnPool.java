package aa.slkjava.thread.pools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executors工具类
 * new ThreadPoolExecutor有五个型参: 核心线程数量, 可允许最大线程数量, 非核心线程无任务保持激活时间, 单位, 一个Runnable队列
 * 核心线程忙---> 添加到queue ---> queue满&&核心忙 ---> 创建非核心线程
 * 当线程没有任务的时候非核心线程经过一段时间等待后会被杀死
 * 线程池中没有空闲线程去执行新任务，此时被submit的新任务会被放到queue中
 * queue满后会触发线程池的拒绝策略
 *
 * 创建线程比较耗费资源。
 * 在线程池中当第二个线程创建出来时第一个线程可能已经执行完了多个任务。主要和任务的轻重有关
 *
 *
 * 线程池的状态：
 * Running处理任务并接收新的任务
 * shutdown不接收新的任务，继续执行所有未处理的任务。调用shutdown()进入此状态
 * stop中断当前的任务，不接收新的任务。调用shutdownNow()进入此状态
 * tidying任务执行完成，然后调用terminated()方法（手动实现，释放资源什么的）
 */
public class A_LearnPool {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

        Future<?> future = threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("xxx");
            }
        });

        System.out.println(".........");
        threadPoolExecutor.shutdown();
    }
}
