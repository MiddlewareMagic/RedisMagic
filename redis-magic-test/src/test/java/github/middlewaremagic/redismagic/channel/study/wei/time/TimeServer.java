package github.middlewaremagic.redismagic.channel.study.wei.time;

import github.middlewaremagic.redismagic.channel.study.wei.DiscardServerHandler;
import github.middlewaremagic.redismagic.channel.study.wei.discard.DiscardServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: `redisRewrite
 * @description:
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-05 15:50
 **/
public class TimeServer {
    private int port;

    public TimeServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口, 接收客户端链接
            ChannelFuture future = bootstrap.bind(port).sync();

            // 等待服务器 socket 关闭
            // 本例不会对数据进行额外处理 可以直接关闭
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 18081;
        }
        new TimeServer(port).run();
    }
}

class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        buf = channelHandlerContext.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) {
        buf.release();
        buf = null;
    }

    @Override
    public void channelActive(final ChannelHandlerContext channelHandlerContext) {
        final ByteBuf time = channelHandlerContext.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));

        final ChannelFuture future = channelHandlerContext.writeAndFlush(time);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture f) throws Exception {
                assert f == future;
                channelHandlerContext.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}