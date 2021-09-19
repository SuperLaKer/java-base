package aa.my.javaio.netty.g_http;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ServerHttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DefaultHttpRequest request = (DefaultHttpRequest) msg;
        System.out.println("server: " + request.uri());

        // 构建返回数据
        String jsonString = JSON.toJSONString(new User("小明", "男", 1));
        ByteBuf buf = Unpooled.copiedBuffer(jsonString.getBytes(StandardCharsets.UTF_8));

        // 构建Context-Type。默认是UTF-8,如果是Windows平台设置为GBK
        DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders(false);
        httpHeaders.set("Context-Type", "application/json;Charset=UTF-8");
        String userAgent = request.headers().get("User-Agent");
        if (userAgent.contains("Windows")) {
            buf = Unpooled.copiedBuffer(jsonString.getBytes(Charset.forName("gbk")));
            httpHeaders.add("Context-Type", "application/json;Charset=GBK");
        }
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.OK, buf);
        response.headers().add(httpHeaders);
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // String address = ctx.channel().remoteAddress().toString();
        // ByteBuf buf = Unpooled.copiedBuffer(address+": ", CharsetUtil.UTF_8);
        // ctx.writeAndFlush(buf);
        ctx.writeAndFlush("msg from server complete。。。");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class User implements Serializable{
    private String name;
    private String gender;
    private Integer age;
}