package github.middlewaremagic.redismagic.channel.study.wei;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @program: `redisRewrite
 * @description: 抛弃服务
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-05 13:53
 **/
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 读取到数据如何处理:
////        ((ByteBuf)msg).release();
//        ByteBuf in = (ByteBuf) msg;
//        try {
////            while(in.isReadable()) {
////                System.out.println((char)in.readByte());
////                System.out.flush();
////            }
//            System.out.println(in.toString(CharsetUtil.US_ASCII));
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
        ctx.write(msg);
        ctx.flush();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis()/1000L+2208988800L));

        final ChannelFuture future = ctx.writeAndFlush(time);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture f) throws Exception {
                assert future == f;
                ctx.close();
            }
        });
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 出现异常 关闭链接即可
        cause.printStackTrace();
        ctx.close();
    }
}
