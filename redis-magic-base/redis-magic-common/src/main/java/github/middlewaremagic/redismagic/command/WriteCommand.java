package github.middlewaremagic.redismagic.command;/*
 * ClassName: WriteCommand
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.resp.Resp;
import io.netty.channel.ChannelHandlerContext;

public interface WriteCommand {
    CommandType type();

    void setContent(Resp[] array);

    void handle(ChannelHandlerContext ctx, RedisCore redisCore);

    /**
     * for aof
     *
     * @param redisCore
     */
    void handle(RedisCore redisCore);
}
