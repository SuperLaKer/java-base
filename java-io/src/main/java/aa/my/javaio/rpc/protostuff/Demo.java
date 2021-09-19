package aa.my.javaio.rpc.protostuff;


/**
 *
 * 8:   1 0 0 0
 * 2:   0 0 1 0
 * |:   1 0 1 0
 * 结果：10
 *
 *
 * tag()length()value()
 * tag: 类型<<<3 | index。index从1开始，string用1表示
 * 第二个属性为string类型用tag可以表示为：1<<<3|2=10。tag = 10
 *
 * varint压缩方式，取7为最高位补1或补0。取7位后剩下的高位有意义补1，无意义补0
 *
 *
 * 负数：整数取反，+1。最高位为1表示负数
 * 0101 0100
 * 1010 1011
 *          10101100
 */
public class Demo {
}
