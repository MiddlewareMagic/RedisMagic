package github.middlewaremagic.redismagic.channel;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;

/**
 * @program: `redisRewrite
 * @description: 自定义本地信道 相关接口
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-08 10:37
 **/
public interface LocalChannelOption<C extends Channel> {

    /**
     * @return 链接获取 线程
     */
    EventLoopGroup boss();

    /**
     * @return 返回处理 tcp 线程
     */
    EventLoopGroup selectors();

    /**
     * @return 返回管道类型
     */
    Class<? extends C> getChannelClass();
}
