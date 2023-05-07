package github.middlewaremagic.redismagic.commandstruct.impl.zset;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.impl.RedisZset;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
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
    public void handle(ChannelHandlerContext ctx, ICache iCache) {
        RedisZset redisZset = (RedisZset) iCache.get(key);
        int remove = redisZset.remove(members);
        ctx.writeAndFlush(new RespInt(remove));
    }

    @Override
    public void handle(ICache iCache) {
        RedisZset redisZset = (RedisZset) iCache.get(key);
        redisZset.remove(members);
    }
}
