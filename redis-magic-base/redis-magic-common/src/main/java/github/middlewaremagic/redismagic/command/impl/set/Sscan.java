package github.middlewaremagic.redismagic.command.impl.set;/*
 * ClassName: Sscan
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.impl.base.AbstraceScan;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisSet;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespArray;

import java.util.List;
import java.util.stream.Collectors;

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
    protected RespArray get(RedisCore redisCore) {
        RedisSet redisSet = (RedisSet) redisCore.get(key);
        List<BulkString> collect = redisSet.keys().stream().map(keyName -> new BulkString(keyName)).collect(Collectors.toList());
        return new RespArray(collect.toArray(new Resp[collect.size()]));
    }
}

