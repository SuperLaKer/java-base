package aa.slkjava.thread.scheduleds;


import lombok.SneakyThrows;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 延期执行：等服务器启动后执行任务
 * 定期执行：心跳，周期执行任务
 */
public class ScheduledApplication {
    public static void main(String[] args) {

        // 延期执行
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
        scheduledPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延时任务...");
            }
        }, 3, TimeUnit.SECONDS);


        // 延期1秒执行一次，之后每隔3秒执行一次。
        // 不管任务执行时间，每个3秒就会产生一个任务放入阻塞队列
        ScheduledExecutorService scheduledPool1 = Executors.newScheduledThreadPool(5);
        scheduledPool1.scheduleAtFixedRate(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("定期任务1...");
                TimeUnit.SECONDS.sleep(12);
            }
        }, 1, 3, TimeUnit.SECONDS);

        // 等任务结束，隔3秒再执行一次任务
        ScheduledExecutorService scheduledPool2 = Executors.newScheduledThreadPool(5);
        scheduledPool2.scheduleWithFixedDelay(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("定期任务2...");
                TimeUnit.SECONDS.sleep(12);
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
