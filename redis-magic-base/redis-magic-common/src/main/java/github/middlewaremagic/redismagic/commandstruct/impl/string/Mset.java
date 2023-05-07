package github.middlewaremagic.redismagic.commandstruct.impl.string;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.SimpleString;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mset implements WriteCommand {
    private List<BytesWrapper> kvList;

    @Override
    public CommandType type() {
        return CommandType.mset;
    }

    @Override
    public void setContent(Resp[] array) {
        kvList = Stream.of(array).skip(1).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache iCache) {
        for (int i = 0; i < kvList.size(); i += 2) {

            iCache.put(kvList.get(i), new RedisString(kvList.get(i + 1)));
        }
        ctx.writeAndFlush(SimpleString.OK);
    }

    @Override
    public void handle(ICache iCache) {
        for (int i = 0; i < kvList.size(); i += 2) {
            iCache.put(kvList.get(i), new RedisString(kvList.get(i + 1)));
        }
    }
}
