package slkjava.jvm.wrapclass;


/**
 * 常量池包含字面量和符号
 * <p>
 * 字面量：字符串、数字常量
 * 符号：敲的代码都是符号，比如方法名、{}、()等等都是符号
 * 符号引用：obj.method();这是符号，在运行时会把method替换为方法区的内存地址
 */
public class AString {
    public static void main(String[] args) {

        // 字符串： == 比较一级指针, equals比较最深的指针
        // 最深的指针是 "abc"在元空间的地址，equals比较为true
        // new String("abc");  会先在堆取开辟空间
        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str1 == str2);  // true
        String str3 = new String("abc");
        String str4 = new String("abc");
        System.out.println(str3.equals(str1));  // true
        System.out.println(str3==str4);  // false，相当于比较两个内存地址肯定不相同
        System.out.println(str3.equals(str4));  // true

        // native方法：源码在JVM中
        String str5 = str1.intern();
        System.out.println(str1 == str5);
    }
}
