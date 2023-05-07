package github.middlewaremagic.redismagic.commandstruct.impl.key;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.*;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.SimpleString;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Type implements Command {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.type;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        Object obj = cache.get(key);
        if (!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", cache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            ctx.writeAndFlush(new SimpleString("none"));
        } else if (redisData instanceof RedisString) {
            ctx.writeAndFlush(new SimpleString("string"));
        } else if (redisData instanceof RedisList) {
            ctx.writeAndFlush(new SimpleString("list"));
        } else if (redisData instanceof RedisSet) {
            ctx.writeAndFlush(new SimpleString("set"));
        } else if (redisData instanceof RedisHash) {
            ctx.writeAndFlush(new SimpleString("hash"));
        } else if (redisData instanceof RedisZset) {
            ctx.writeAndFlush(new SimpleString("zset"));
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
