package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Ttl
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

public class Ttl implements WriteCommand {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.ttl;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            ctx.writeAndFlush(new RespInt(-2));
        } else if (redisData.timeout() == -1) {
            ctx.writeAndFlush(new RespInt(-1));
        } else {
            long second = (redisData.timeout() - System.currentTimeMillis()) / 1000;
            ctx.writeAndFlush(new RespInt((int) second));
        }
    }

    @Override
    public void handle(RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
        } else if (redisData.timeout() == -1) {
        } else {
            long second = (redisData.timeout() - System.currentTimeMillis()) / 1000;
        }
    }
}
