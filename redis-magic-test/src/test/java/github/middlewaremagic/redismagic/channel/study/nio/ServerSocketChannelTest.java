package github.middlewaremagic.redismagic.channel.study.nio;

//import io.netty.channel.socket.ServerSocketChannel;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @program: `redisRewrite
 * @description: NIO 原生开发 我淦
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-05 10:03
 **/
public class ServerSocketChannelTest {

    @Test
    public void testSocketChannel() {
        int port = 18808;
        try {
            // 创建 socket channel
            ServerSocketChannel acceptorSvr = ServerSocketChannel.open();

            // 绑定端口, 设置为非阻塞模式
            acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"), port));
            acceptorSvr.configureBlocking(false);
            // 启动多路复用
            Selector selector = Selector.open();
            // 将 ServerSocketChannel 注册到 Reactor 线程的多路复用器上
            // 并监听 ACCEPT 事件
//            SelectionKey key = acceptorSvr.register(selector, SelectionKey.OP_ACCEPT, ioH)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
