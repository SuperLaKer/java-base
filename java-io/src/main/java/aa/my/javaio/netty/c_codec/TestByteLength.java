package aa.my.javaio.netty.c_codec;

// 测试字符串占用几个字节
public class TestByteLength {
    public static void main(String[] args) {
        System.out.println("a".getBytes().length);
        System.out.println("aa".getBytes().length);
        System.out.println("你".getBytes().length);
        System.out.println("你你".getBytes().length);
    }
}
