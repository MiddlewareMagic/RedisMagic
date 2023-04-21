package github.middlewaremagic.redismagic.command.impl.string;/*
 * ClassName: SetNx
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisString;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

public class SetNx implements WriteCommand {
    private BytesWrapper key;
    private BytesWrapper value;

    @Override
    public CommandType type() {
        return CommandType.setnx;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        value = ((BulkString) array[2]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        boolean exist = redisCore.exist(key);
        if (exist) {
            ctx.writeAndFlush(new RespInt(0));
        } else {
            RedisString redisString = new RedisString();
            redisString.setValue(value);
            redisCore.put(key, redisString);
            ctx.writeAndFlush(new RespInt(1));
        }
    }

    @Override
    public void handle(RedisCore redisCore) {
        boolean exist = redisCore.exist(key);
        if (exist) {
        } else {
            RedisString redisString = new RedisString();
            redisString.setValue(value);
            redisCore.put(key, redisString);

        }
    }
}
