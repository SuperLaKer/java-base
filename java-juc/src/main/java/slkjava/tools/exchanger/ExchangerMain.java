package slkjava.tools.exchanger;


import java.util.concurrent.Exchanger;

/**
 * 两个线程之间交换数据
 */
public class ExchangerMain {
    public static void main(String[] args) throws InterruptedException {
        final Exchanger<Integer> exchanger = new Exchanger<Integer>();


        // 传递给子线程, 交换数据+
        Integer data = 1;
        Integer exchangeData = exchanger.exchange(data);
        System.out.println(exchangeData);

    }
}
