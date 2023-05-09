package github.middlewaremagic.redismagic.impl;

import github.middlewaremagic.redismagic.JRedisServer;
import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.bs.CacheBs;
import github.middlewaremagic.redismagic.command.CommandDecoder;
import github.middlewaremagic.redismagic.command.CommandHandler;
import github.middlewaremagic.redismagic.core.RedisCore;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.response.ResponseEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @program: `redisRewrite
 * @description: java 版 redis 实现类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-07 22:53
 **/
@Slf4j
public class JRedisServerImpl implements JRedisServer {

    ICache<BytesWrapper, RedisData> cache;
    ServerBootstrap serverBootstrap;
    EventExecutorGroup singleEventExecutor;

    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup(1);

    String localhost = "127.0.0.1";
    int port = 15125;

    public JRedisServerImpl() {
        cache = CacheBs.<BytesWrapper,RedisData>newInstance().build();
        serverBootstrap = new ServerBootstrap();
        singleEventExecutor = new NioEventLoopGroup(1);
    }

    @Override
    public void start() {
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                //false
                .option(ChannelOption.SO_KEEPALIVE, true)
//                .childOption(ChannelOption.TCP_NODELAY, true)
//                .childOption(ChannelOption.SO_SNDBUF, 65535)
//                .childOption(ChannelOption.SO_RCVBUF, 65535)
                .localAddress(new InetSocketAddress(localhost, port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        channelPipeline.addLast(
                                new ResponseEncoder(),
                                new CommandDecoder()//,
//                                /*心跳,管理长连接*/
//                                new IdleStateHandler(0, 0, 20)
                        );
                        channelPipeline.addLast(singleEventExecutor, new CommandHandler(cache));
                    }
                });

        try {
            ChannelFuture sync = serverBootstrap.bind().sync();
            log.info(sync.channel().localAddress().toString());
        } catch (InterruptedException e) {
//
            log.warn("Interrupted!", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            singleEventExecutor.shutdownGracefully();
        } catch (Exception e) {
            log.warn("关闭 netty资源");
        }
    }

}
