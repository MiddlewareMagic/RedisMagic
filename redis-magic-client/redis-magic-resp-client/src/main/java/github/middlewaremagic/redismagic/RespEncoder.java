package github.middlewaremagic.redismagic;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @program: `redisRewrite
 * @description: RESP 协议 编码实现类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-06 15:47
 **/
public class RespEncoder extends MessageToMessageEncoder<CharSequence> {

    private final Charset charset;

    public RespEncoder() {
        this(Charset.defaultCharset());
    }

    public RespEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, CharSequence msg, List<Object> out) throws Exception {

    }
}
