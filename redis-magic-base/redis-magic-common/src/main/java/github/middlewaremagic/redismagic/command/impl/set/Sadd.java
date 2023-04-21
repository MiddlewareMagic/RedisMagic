package github.middlewaremagic.redismagic.command.impl.set;/*
 * ClassName: Sadd
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;
import github.middlewaremagic.redismagic.datatype.RedisSet;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            RedisSet redisSet = new RedisSet();
            int sadd = redisSet.sadd(member);
            redisCore.put(key, redisSet);
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
    public void handle(RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            RedisSet redisSet = new RedisSet();
            redisSet.sadd(member);
            redisCore.put(key, redisSet);
        } else if (redisData instanceof RedisSet) {
            RedisSet redisSet = (RedisSet) redisData;
            redisSet.sadd(member);
        } else {
            throw new IllegalArgumentException("类型不匹配");
        }
    }
}
