package github.middlewaremagic.redismagic.response;

import github.middlewaremagic.redismagic.respstruct.Resp;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @program: `redisRewrite
 * @description: 二进制转换 编码类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-07 21:56
 **/
public class ResponseEncoder extends MessageToByteEncoder<Resp> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Resp resp, ByteBuf byteBuf) throws Exception {
        try {
            Resp.write(resp, byteBuf);//msg.encode();
            byteBuf.writeBytes(byteBuf);
            // logger.info(byteBuffer.toString());
        } catch (Exception e) {
            channelHandlerContext.close();
        }
    }

}
