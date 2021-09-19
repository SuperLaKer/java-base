package slkjava.jvm;

/**
 * 二进制位运算
 * 原码：第一位是符号位
 * 正数反码 == 原码
 * 负数反码 == 符号位1不变，其他位取反
 * 补码：
 * 正数补码 == 原码
 * 负数补码 == 反码+1
 * <p>
 * 加法运算：原码相加
 * 减法运算：补码相加、反码相加(不用)
 * 反码相加：0会出现两种表示方式10000000和00000000即+0和-0
 */
public class BitComputed {
    public static void main(String[] args) {
        // 5*2^x
        System.out.println(5 << 1);  // 10
        System.out.println(5 << 2);  // 20
        System.out.println(5 << 3);  // 40
        // 0000 0101 | 0000 0010
        System.out.println(5 | 2);
        // 0000 0101 & 0000 0010
        System.out.println(5 & 2);
    }
}
