package github.middlewaremagic.redismagic.command.impl.hash;/*
 * ClassName: Hdel
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisHash;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hdel implements WriteCommand {
    private BytesWrapper key;
    private List<BytesWrapper> fields;

    @Override
    public CommandType type() {
        return CommandType.hdel;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        fields = Stream.of(array).skip(2).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisHash redisHash = (RedisHash) redisCore.get(key);
        int del = redisHash.del(fields);
        ctx.writeAndFlush(new RespInt(del));
    }

    @Override
    public void handle(RedisCore redisCore) {
        RedisHash redisHash = (RedisHash) redisCore.get(key);
        redisHash.del(fields);
    }
}
