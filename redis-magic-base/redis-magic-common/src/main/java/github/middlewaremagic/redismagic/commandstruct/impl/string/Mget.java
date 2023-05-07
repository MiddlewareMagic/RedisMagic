package github.middlewaremagic.redismagic.commandstruct.impl.string;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespArray;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Mget implements Command {
    private List<BytesWrapper> keys;

    @Override
    public CommandType type() {
        return CommandType.mget;
    }

    @Override
    public void setContent(Resp[] array) {
        keys = Stream.of(array).skip(1).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache iCache) {
        LinkedList<BytesWrapper> linkedList = new LinkedList();
        keys.forEach(key -> {
            Object obj = iCache.get(key);
            if(!(obj instanceof RedisData)) {
                log.error("ICache type error. Please check out. {}", iCache.toString());
                return;
            }
            RedisData redisData = (RedisData) obj;
            if (redisData == null) {
            } else if (redisData instanceof RedisString) {
                linkedList.add(((RedisString) redisData).getValue());
            } else {
                throw new UnsupportedOperationException();
            }
        });
        RespArray respArray = new RespArray(linkedList.stream().map(BulkString::new).toArray(Resp[]::new));
        ctx.writeAndFlush(respArray);
    }

}
