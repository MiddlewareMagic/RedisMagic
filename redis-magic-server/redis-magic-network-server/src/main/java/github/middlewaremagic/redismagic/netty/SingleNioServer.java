package github.middlewaremagic.redismagic.netty;

import github.middlewaremagic.redismagic.netty.handler.MainNioServerHandler;
import github.middlewaremagic.redismagic.netty.handler.MainNioServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: `redisRewrite
 * @description: 单 workerLoop nio 服务启动类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-06 10:11
 **/
public class SingleNioServer {

    private int port;

    private static SingleNioServer singleNioServer;

    private MainNioServerHandler mainNioServerHandler;

    private MainNioServerInitializer mainNioServerInitializer;

    public static SingleNioServer getInstance(int port) {
        if(singleNioServer == null)
            singleNioServer = new SingleNioServer(port);
        return singleNioServer;
    }

    private SingleNioServer(int port) {
        this.port = port;
        mainNioServerInitializer = new MainNioServerInitializer();
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MainNioServerInitializer())
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
}
