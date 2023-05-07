package github.middlewaremagic.redismagic.commandstruct.impl.set;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisSet;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Sadd implements WriteCommand {
    List<BytesWrapper> member;
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.sadd;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        member = Stream.of(array).skip(2).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
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
            RedisSet redisSet = new RedisSet();
            int sadd = redisSet.sadd(member);
            cache.put(key, redisSet);
            ctx.writeAndFlush(new RespInt(sadd));
        } else if (redisData instanceof RedisSet) {
            RedisSet redisSet = (RedisSet) redisData;
            int sadd = redisSet.sadd(member);
            ctx.writeAndFlush(new RespInt(sadd));
        } else {
            throw new IllegalArgumentException("类型不匹配");
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
            RedisSet redisSet = new RedisSet();
            redisSet.sadd(member);
            cache.put(key, redisSet);
        } else if (redisData instanceof RedisSet) {
            RedisSet redisSet = (RedisSet) redisData;
            redisSet.sadd(member);
        } else {
            throw new IllegalArgumentException("类型不匹配");
        }
    }
}
