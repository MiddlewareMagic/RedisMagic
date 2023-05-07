package github.middlewaremagic.redismagic.commandstruct.impl.string;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.SimpleString;
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
    public void handle(ChannelHandlerContext ctx, ICache iCache) {
        RedisString redisString = new RedisString();
        redisString.setValue(value);
        long outTime = System.currentTimeMillis() + (seconds * 1000L);
        iCache.put(key, redisString);
        iCache.expireAt(key, outTime);
        ctx.writeAndFlush(SimpleString.OK);
    }

    @Override
    public void handle(ICache iCache) {
        RedisString redisString = new RedisString();
        redisString.setValue(value);
        long outTime = System.currentTimeMillis() + (seconds * 1000L);
        iCache.put(key, redisString);
        iCache.expireAt(key, outTime);
    }
}
