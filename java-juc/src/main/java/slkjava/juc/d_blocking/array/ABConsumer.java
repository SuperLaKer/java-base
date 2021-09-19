package slkjava.juc.d_blocking.array;

public class ABConsumer {
    public static void main(String[] args) {
        while (true) {
            try {
                String take = StaticQueue.queue.take();
                System.out.println(take);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
