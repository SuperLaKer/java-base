package slkjava.jvm.cgc;

/**
 * JAVA对象分配模型：
 * young: Eden、Survivor(from、to) 8:1:1
 * young:old = 1:2
 * 优先Eden(标记整理) -> from <=复制算法=> to -> old(标记整理算法)
 * 经历15次gc进入old
 * <p>
 */
public class AMemoryModel {
}
