package slkjava.jvm.bLoadOrder;


public class Base {

    static int a;
    private static volatile Base base;

    static {
        System.out.printf("a: %d\n", a);
        System.out.println("base: 静态代码块");
    }


    public Base() {
        System.out.println("base: 无参...");
    }

    public static Base getInstance(String str) {
        System.out.println(str);
        if (base == null) {
            base = new Base();
        }
        return base;
    }
}
