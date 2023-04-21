package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Expire
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

public class Expire implements WriteCommand {
    private BytesWrapper key;
    private int second;

    @Override
    public CommandType type() {
        return CommandType.expire;
    }


    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        second = Integer.parseInt(((BulkString) array[2]).getContent().toUtf8String());
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            ctx.writeAndFlush(new RespInt(0));
        } else {
            redisData.setTimeout(System.currentTimeMillis() + (second * 1000));
            ctx.writeAndFlush(new RespInt(1));
        }
    }

    @Override
    public void handle(RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
        } else {
            redisData.setTimeout(System.currentTimeMillis() + (second * 1000));
        }
    }
}
