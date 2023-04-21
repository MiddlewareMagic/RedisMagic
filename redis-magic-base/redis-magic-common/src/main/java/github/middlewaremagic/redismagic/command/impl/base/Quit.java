package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Quit
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.SimpleString;
import io.netty.channel.ChannelHandlerContext;

public class Quit implements Command {
    @Override
    public CommandType type() {
        return CommandType.quit;
    }

    @Override
    public void setContent(Resp[] array) {
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        ctx.writeAndFlush(SimpleString.OK);
        ctx.close();
    }
}