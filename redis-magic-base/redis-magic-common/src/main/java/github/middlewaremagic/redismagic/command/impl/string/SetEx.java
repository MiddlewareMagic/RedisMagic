package github.middlewaremagic.redismagic.command.impl.string;/*
 * ClassName: SetEx
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
import github.middlewaremagic.redismagic.resp.SimpleString;
import io.netty.channel.ChannelHandlerContext;

public class SetEx implements WriteCommand {
    private BytesWrapper key;
    private int seconds;
    private BytesWrapper value;

    @Override
    public CommandType type() {
        return CommandType.setex;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        seconds = Integer.parseInt(((BulkString) array[2]).getContent().toUtf8String());
        value = ((BulkString) array[3]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisString redisString = new RedisString();
        redisString.setValue(value);
        redisString.setTimeout(System.currentTimeMillis() + (seconds * 1000L));
        redisCore.put(key, redisString);
        ctx.writeAndFlush(SimpleString.OK);
    }

    @Override
    public void handle(RedisCore redisCore) {
        RedisString redisString = new RedisString();
        redisString.setValue(value);
        redisString.setTimeout(System.currentTimeMillis() + (seconds * 1000L));
        redisCore.put(key, redisString);
    }
}

