package slkjava.jvm.cgc;


/**
 * 垃圾回收与JVM优化
 * -XX:+PrintGCDetails
 * Eden空间不足会触发young gc
 *
 *
 *
 */
public class GarbageCollection {
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation, allocation6;

        // 大对象可能直接放到老年代
        allocation1 = new byte[65000 * 1024];
        // allocation2 = new byte[8000*1024];
        // allocation3 = new byte[1000*1024];
        // allocation4 = new byte[1000*1024];
        // allocation5 = new byte[1000*1024];
        // allocation6 = new byte[1000*1024];
    }
}
