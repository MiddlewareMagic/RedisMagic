package github.middlewaremagic.redismagic.command.impl.string;/*
 * ClassName: Get
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;
import github.middlewaremagic.redismagic.datatype.RedisString;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import io.netty.channel.ChannelHandlerContext;

public class Get implements Command {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.get;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            ctx.writeAndFlush(BulkString.NullBulkString);
        } else if (redisData instanceof RedisString) {
            BytesWrapper value = ((RedisString) redisData).getValue();
            ctx.writeAndFlush(new BulkString(value));
        } else {
            throw new UnsupportedOperationException();
        }
    }
}

