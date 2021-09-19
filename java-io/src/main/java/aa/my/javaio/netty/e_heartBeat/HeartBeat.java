package aa.my.javaio.netty.e_heartBeat;

import io.netty.handler.timeout.IdleStateHandler;

/**
 * netty心跳处理机制是靠一个Handler实现的
 * * // An example that sends a ping message when there is no outbound traffic
 * * // for 30 seconds.  The connection is closed when there is no inbound traffic
 * * // for 60 seconds.
 */
public class HeartBeat {
    public static void main(String[] args) {
        int readTimeout = 90;
        int writeTimeout = 60;
        int allTimeout = 0;


        /**
         * 创建一个触发 {@link IdleStateEvent} 的新实例。
         *
         * @param readerIdleTimeSeconds
         *        在指定的时间段内没有执行读取时，将触发状态为 {@link IdleStateREADER_IDLE} 的 {@link IdleStateEvent}。
         *        指定 {@code 0} 以禁用。
         * @param writerIdleTimeSeconds
         *        当在指定的时间段内没有执行写入时，将触发状态为 {@link IdleStateWRITER_IDLE} 的 {@link IdleStateEvent}。
         *        指定 {@code 0} 以禁用。
         * @param allIdleTimeSeconds
         *        当在指定的时间段内未执行读取和写入操作时，将触发状态为 {@link IdleStateALL_IDLE} 的 {@link IdleStateEvent}。
         *        指定 {@code 0} 以禁用。
         */
        IdleStateHandler idleStateHandler = new IdleStateHandler(readTimeout, writeTimeout, allTimeout);
        // 把idleStateHandler添加进pipe就行了
    }
}
