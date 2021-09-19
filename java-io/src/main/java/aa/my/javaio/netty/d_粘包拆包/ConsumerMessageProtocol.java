package aa.my.javaio.netty.d_粘包拆包;

import lombok.Data;

@Data
public class ConsumerMessageProtocol {
    // 一次发送：包体长度
    private int len;
    // 一次发送：包体内容
    private byte[] content;
}
