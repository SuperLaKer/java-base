package slkjava.juc.a_thread.interrupt;

public class InterruptMain {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            Thread thread_self = Thread.currentThread();

            for (long i = 0; i < 1000; i++) {
                // 没有到点...
                if (!thread_self.isInterrupted()) {
                    System.out.println("干活" + i);
                }

                // 到点了...
                if (thread_self.isInterrupted()) {
                    // 没有干完一半...
                    if (i < 600) {
                        System.out.println("加班...");
                        Thread.interrupted();
                    } else {
                        System.out.println("完成:" + i);
                        thread_self.setName("下班了...");
                        System.out.println("溜了...");
                        break;
                    }
                }
            }
        });
        try {
            thread.join();
            thread.start();
        } catch (InterruptedException e) {
            System.out.println("异常:" + e.getMessage());
        }
        try {
            Thread.sleep(5);
            String name = thread.getName();
            while (!name.equals("下班了...")) {
                thread.interrupt();
                name = thread.getName();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
