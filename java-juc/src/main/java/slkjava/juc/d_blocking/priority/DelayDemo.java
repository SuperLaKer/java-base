package slkjava.juc.d_blocking.priority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import slkjava.juc.d_blocking.dependencies.MyRandom;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 其中一个元素只有在其延迟到期时才能被取走
 * 尾部的最先过期、如果没有延迟到期，则没有头部，{poll} 将返回 {null}。
 * 当元素的 {getDelay(TimeUnit.NANOOSECONDS)} 方法返回小于或等于零的值时发生过期
 */
public class DelayDemo {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<MovieTicket> delayQueue = new DelayQueue<>();
        Tick2 tick2 = new Tick2();
        Random random = new Random();
        int num = 10;
        for (int i = 0; i < num; i++) {
            delayQueue.add(new MovieTicket(random.nextInt(50), MyRandom.str(10)));
        }
        while (delayQueue.size() > 0) {
            MovieTicket take = delayQueue.take();
            System.out.println(take);
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class MovieTicket implements Delayed {
        static Integer stander = 0;
        private Integer time;
        private String str;

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(stander - this.time, TimeUnit.SECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (o.getDelay(TimeUnit.SECONDS) - getDelay(TimeUnit.SECONDS));
        }
    }


    @NoArgsConstructor
    @Data
    public static class Tick2 implements Comparable<Tick2> {
        private Integer time;
        private String str;

        public Tick2(Integer time, String str) {
            this.time = time;
            this.str = str;
        }

        @Override
        public int compareTo(Tick2 o) {
            return this.time - o.time;
        }
    }
}
