package github.middlewaremagic.redismagic;

import github.middlewaremagic.redismagic.data.RespTable;
import github.middlewaremagic.redismagic.datatype.RESP;
import github.middlewaremagic.redismagic.datatype.impl.*;
import github.middlewaremagic.redismagic.parser.UnWrapperCommandList;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.internal.ObjectUtil;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: `redisRewrite
 * @description:
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-06 15:19
 **/
public class RespDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Charset charset;

    public RespDecoder() {
        this(Charset.defaultCharset());
    }

    public RespDecoder(Charset charset) {
        this.charset = ObjectUtil.checkNotNull(charset, "charset");
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        String respStr = msg.toString(charset);
        List<String> result = RespTable.parseResp(respStr);
        out.addAll(result);
    }
}
