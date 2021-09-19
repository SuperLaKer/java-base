package aa.slkjava.thread;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyThread myThread = new MyThread(countDownLatch);
        myThread.start();
        System.out.println(myThread.getState().name());

        myThread.interrupt();
        System.out.println("主："+myThread.isInterrupted());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主："+myThread.isInterrupted());
        myThread.join();
    }
}

class MyThread extends Thread {

    CountDownLatch countDownLatch;

    public MyThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {
    }
}
