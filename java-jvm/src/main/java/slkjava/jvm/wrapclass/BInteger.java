package slkjava.jvm.wrapclass;

/**
 * [-128, 127]
 * Integer类搜索127
 */
public class BInteger {
    public static void main(String[] args) {
        Integer integer1 = 127;
        Integer integer2 = 127;
        System.out.println(integer1 == integer2);

        Integer integer3 = 128;
        Integer integer4 = 128;
        System.out.println(integer3 == integer4);
    }
}
