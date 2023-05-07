package github.middlewaremagic.redismagic.commandstruct.impl.list;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisList;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespArray;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Lrange implements Command {
    BytesWrapper key;
    int start;
    int end;

    @Override
    public CommandType type() {
        return CommandType.lrange;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        start = Integer.parseInt(((BulkString) array[2]).getContent().toUtf8String());
        end = Integer.parseInt(((BulkString) array[3]).getContent().toUtf8String());
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
        List<BytesWrapper> lrang = redisList.lrang(start, end);
        RespArray respArray = new RespArray(lrang.stream().map(BulkString::new).toArray(Resp[]::new));
        ctx.writeAndFlush(respArray);
    }
}
