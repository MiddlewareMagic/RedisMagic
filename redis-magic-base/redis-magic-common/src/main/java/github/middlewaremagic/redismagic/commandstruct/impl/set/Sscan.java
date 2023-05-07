package github.middlewaremagic.redismagic.commandstruct.impl.set;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisSet;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespArray;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Sscan extends AbstraceScan {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.sscan;
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
        RedisSet redisSet = (RedisSet) redisData;
        List<BulkString> collect = redisSet.keys().stream().map(keyName -> new BulkString(keyName)).collect(Collectors.toList());
        return new RespArray(collect.toArray(new Resp[collect.size()]));
    }
}
