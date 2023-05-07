package github.middlewaremagic.redismagic.commandstruct.impl.list;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisList;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lrem implements WriteCommand {
    private BytesWrapper key;
    private BytesWrapper value;

    @Override
    public CommandType type() {
        return CommandType.lrem;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        value = ((BulkString) array[3]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        Object obj = cache.get(key);
        if (!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", cache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        RedisList redisList = (RedisList) redisData;
        int remove = redisList.remove(value);
        ctx.writeAndFlush(new RespInt(remove));
    }

    @Override
    public void handle(ICache cache) {
        Object obj = cache.get(key);
        if (!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", cache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        RedisList redisList = (RedisList) redisData;
        redisList.remove(value);
    }
}
