package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Exists
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

public class Exists implements Command {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.exists;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        boolean exist = redisCore.exist(key);
        if (exist) {
            ctx.writeAndFlush(new RespInt(1));
        } else {
            ctx.writeAndFlush(new RespInt(0));
        }
    }
}
