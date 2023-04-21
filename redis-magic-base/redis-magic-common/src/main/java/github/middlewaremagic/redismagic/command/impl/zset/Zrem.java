package github.middlewaremagic.redismagic.command.impl.zset;/*
 * ClassName: Zrem
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisZset;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zrem implements WriteCommand {
    private BytesWrapper key;
    private List<BytesWrapper> members;

    @Override
    public CommandType type() {
        return CommandType.zrem;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        members = Stream.of(array).skip(2).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisZset redisZset = (RedisZset) redisCore.get(key);
        int remove = redisZset.remove(members);
        ctx.writeAndFlush(new RespInt(remove));
    }

    @Override
    public void handle(RedisCore redisCore) {
        RedisZset redisZset = (RedisZset) redisCore.get(key);
        redisZset.remove(members);
    }
}

