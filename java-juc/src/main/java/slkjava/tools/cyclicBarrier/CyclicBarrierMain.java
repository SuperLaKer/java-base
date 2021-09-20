package slkjava.tools.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏屏障
 * 所有线程到达屏障 才能继续往下执行
 * <p>
 * 等待所有(80%)爬虫爬取完数据，在进行下一步信息处理
 */
public class CyclicBarrierMain {

    public static void main(String[] args) throws Exception {

        // 子线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("正在爬取数据...");
            int time = new Random(10).nextInt();
            try {
                Thread.sleep(time * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 主线程先await等待爬虫爬取信息
        cyclicBarrier.await();
        // 处理信息: 数据处理、数据分析、特征工程
    }

}
