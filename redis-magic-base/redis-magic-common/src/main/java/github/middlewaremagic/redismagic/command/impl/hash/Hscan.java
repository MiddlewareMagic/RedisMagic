package github.middlewaremagic.redismagic.command.impl.hash;/*
 * ClassName: Hscan
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.impl.base.AbstraceScan;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisHash;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespArray;

import java.util.Map;
import java.util.stream.Stream;

public class Hscan extends AbstraceScan {
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
    protected RespArray get(RedisCore redisCore) {
        RedisHash redisHash = (RedisHash) redisCore.get(key);
        Map<BytesWrapper, BytesWrapper> map = redisHash.getMap();
        return new RespArray(map.entrySet().stream().flatMap(entry -> {
            Resp[] resps = new Resp[2];
            resps[0] = new BulkString(entry.getKey());
            resps[1] = new BulkString(entry.getValue());
            return Stream.of(resps);
        }).toArray(Resp[]::new));
    }
}

