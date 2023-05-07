package github.middlewaremagic.redismagic.commandstruct.impl.hash;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.impl.AbstractScan;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisHash;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespArray;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class Hscan extends AbstractScan {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.hscan;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
    }

    @Override
    protected RespArray get(ICache cache) {
        Object obj = cache.get(key);
        if (!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", cache.toString());
            return null;
        }
        RedisData redisData = (RedisData) obj;
        RedisHash redisHash = (RedisHash) redisData;
        Map<BytesWrapper, BytesWrapper> map = redisHash.getMap();
        return new RespArray(map.entrySet().stream().flatMap(entry -> {
            Resp[] resps = new Resp[2];
            resps[0] = new BulkString(entry.getKey());
            resps[1] = new BulkString(entry.getValue());
            return Stream.of(resps);
        }).toArray(Resp[]::new));
    }
}
