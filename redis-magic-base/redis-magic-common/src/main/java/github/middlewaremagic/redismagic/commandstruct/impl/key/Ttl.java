package github.middlewaremagic.redismagic.commandstruct.impl.key;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        Object obj = cache.get(key);
        if (!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", cache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            ctx.writeAndFlush(new RespInt(-2));
        } else if (cache.expire().expireTime(key) == -1) {
            ctx.writeAndFlush(new RespInt(-1));
        } else {
            long second = (cache.expire().expireTime(key) - System.currentTimeMillis()) / 1000;
            ctx.writeAndFlush(new RespInt((int) second));
        }
    }

    @Override
    public void handle(ICache cache) {
        Object obj = cache.get(key);
        if (!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", cache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
        } else if (cache.expire().expireTime(key) == -1) {
        } else {
            long second = (cache.expire().expireTime(key) - System.currentTimeMillis()) / 1000;
        }
    }
}
