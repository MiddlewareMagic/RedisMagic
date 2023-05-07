package github.middlewaremagic.redismagic.commandstruct.impl.string;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
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
    public void handle(ChannelHandlerContext ctx, ICache iCache) {
        boolean exist = iCache.containsKey(key);
        if (exist) {
            ctx.writeAndFlush(new RespInt(0));
        } else {
            RedisString redisString = new RedisString();
            redisString.setValue(value);
            iCache.put(key, redisString);
            ctx.writeAndFlush(new RespInt(1));
        }
    }

    @Override
    public void handle(ICache iCache) {
        boolean exist = iCache.containsKey(key);
        if (exist) {
        } else {
            RedisString redisString = new RedisString();
            redisString.setValue(value);
            iCache.put(key, redisString);

        }
    }
}
