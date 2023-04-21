package github.middlewaremagic.redismagic.command.impl.zset;/*
 * ClassName: Zadd
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;
import github.middlewaremagic.redismagic.datatype.RedisZset;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

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
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            RedisZset redisZset = new RedisZset();
            int add = redisZset.add(keys);
            redisCore.put(key, redisZset);
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
    public void handle(RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            RedisZset redisZset = new RedisZset();
            int add = redisZset.add(keys);
            redisCore.put(key, redisZset);
        } else if (redisData instanceof RedisZset) {
            RedisZset redisZset = (RedisZset) redisData;
            int add = redisZset.add(keys);
        } else {
            throw new UnsupportedOperationException("类型不匹配");
        }
    }
}

