package slkjava.jvm.bLoadOrder;


/**
 * 加载：new TheChild();
 * 验证：验证字节码完整性
 * 准备：给类的静态变量分配内存，并赋予默认值
 * <p>
 * 构造方法第一行默认调用: super.无参();
 * <p>
 * 准备阶段：静态属性赋予默认值
 * 然后加载本类的静态代码块
 * <p>
 * 静态初始化完成之后执行构无参造方法
 */
public class TheChild extends TheParent {

    static Base base = Base.getInstance("子类: 静态属性");
    static int integer;

    static {
        System.out.println(integer);
        System.out.println("子类: 静态代码块1...");
    }

    static {
        System.out.println("子类: 静态代码块2...");
    }

    public TheChild() {
        System.out.println("子类: 无参...");
    }

    public static void main(String[] args) {
        TheChild theChild = new TheChild();
        System.out.println(theChild);
    }
}
