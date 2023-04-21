package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Select
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.SimpleString;
import io.netty.channel.ChannelHandlerContext;

public class Select implements Command {
    private Integer index;

    @Override
    public CommandType type() {
        return CommandType.select;
    }

    @Override
    public void setContent(Resp[] array) {
        index = Integer.parseInt(((BulkString) array[1]).getContent().toUtf8String());
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        if (index > 0) {
            SimpleString ok = new SimpleString("-ERR invalid DB index");
            ctx.writeAndFlush(ok);
        } else {
            SimpleString ok = new SimpleString("OK");
            ctx.writeAndFlush(ok);
        }

    }
}
