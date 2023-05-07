package github.middlewaremagic.redismagic.commandstruct.impl.zset;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisZset;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Zadd implements WriteCommand {
    private BytesWrapper key;
    private List<RedisZset.ZsetKey> keys;

    @Override
    public CommandType type() {
        return CommandType.zadd;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        keys = new ArrayList<>();
        for (int i = 2; i + 1 < array.length; i += 2) {
            long score = Long.parseLong(((BulkString) array[i]).getContent().toUtf8String());
            BytesWrapper member = ((BulkString) array[i + 1]).getContent();
            keys.add(new RedisZset.ZsetKey(member, score));
        }
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache iCache) {
        Object obj = iCache.get(key);
        if(!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", iCache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            RedisZset redisZset = new RedisZset();
            int add = redisZset.add(keys);
            iCache.put(key, redisZset);
            ctx.writeAndFlush(new RespInt(add));
        } else if (redisData instanceof RedisZset) {
            RedisZset redisZset = (RedisZset) redisData;
            int add = redisZset.add(keys);
            ctx.writeAndFlush(new RespInt(add));
        } else {
            throw new UnsupportedOperationException("类型不匹配");
        }
    }

    @Override
    public void handle(ICache iCache) {
        Object obj = iCache.get(key);
        if(!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", iCache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            RedisZset redisZset = new RedisZset();
            int add = redisZset.add(keys);
            iCache.put(key, redisZset);
        } else if (redisData instanceof RedisZset) {
            RedisZset redisZset = (RedisZset) redisData;
            int add = redisZset.add(keys);
        } else {
            throw new UnsupportedOperationException("类型不匹配");
        }
    }
}
