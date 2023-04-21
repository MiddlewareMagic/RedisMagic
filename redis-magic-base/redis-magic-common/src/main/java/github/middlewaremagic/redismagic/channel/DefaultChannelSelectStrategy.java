package github.middlewaremagic.redismagic.channel;/*
 * ClassName: DefaultChannelSelectStrategy
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

import github.middlewaremagic.redismagic.channel.epoll.EpollChannelOption;
import github.middlewaremagic.redismagic.channel.kqueue.KqueueChannelOption;
import github.middlewaremagic.redismagic.channel.select.NioSelectChannelOption;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.kqueue.KQueue;

public class DefaultChannelSelectStrategy implements ChannelSelectStrategy {
    @Override
    public LocalChannelOption select() {

        if (KQueue.isAvailable()) {
            return new KqueueChannelOption();
        }
        if (Epoll.isAvailable()) {
            return new EpollChannelOption();
        }
        return new NioSelectChannelOption();
    }
}

