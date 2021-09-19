package slkjava.jvm.bLoadOrder;

public class BBase extends Base {
    static {
        System.out.println("BBase static...");
    }

    public BBase() {
        System.out.println("BBase: 无参。。。");
    }

    public static void main(String[] args) {
        BBase bBase = new BBase();
        System.out.println(bBase);
    }
}
