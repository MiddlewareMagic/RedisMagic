package github.middlewaremagic.redismagic.command;/*
 * ClassName: CommandHandler
 * Description:
 * @Author: zjh
 * @Create: 2023/5/5
 */

import github.middlewaremagic.redismagic.core.RedisCore;
import github.middlewaremagic.redismagic.utils.TRACEID;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

public class CommandHandler {

    private final RedisCore redisCore;

    public CommandHandler(RedisCore redisCore) {
        this.redisCore = redisCore;
    }

    public RedisCore getRedisCore() {
        return redisCore;
    }
}
