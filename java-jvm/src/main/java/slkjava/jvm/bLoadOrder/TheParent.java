package slkjava.jvm.bLoadOrder;

public class TheParent {
    static Base base = Base.getInstance("父类: 静态属性");

    static {
        System.out.println("父类: 静态代码块1...");
    }

    static {
        System.out.println("父类: 静态代码块2...");
    }

    public TheParent() {
        System.out.println("父类: 无参...");
    }
}
