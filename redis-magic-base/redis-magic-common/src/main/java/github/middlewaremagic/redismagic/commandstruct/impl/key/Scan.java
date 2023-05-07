package github.middlewaremagic.redismagic.commandstruct.impl.key;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespArray;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class Scan implements Command {
    @Override
    public CommandType type() {
        return CommandType.scan;
    }

    @Override
    public void setContent(Resp[] array) {
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        Resp[] array = new Resp[2];
        BulkString blukStrings = new BulkString(new BytesWrapper("0".getBytes(CHARSET)));
        array[0] = blukStrings;
        Set<BytesWrapper> keySet = cache.keySet();
        List<BulkString> collect = keySet.stream().map(keyName -> new BulkString(keyName)).collect(Collectors.toList());
        array[1] = new RespArray(collect.toArray(new Resp[collect.size()]));
        ctx.writeAndFlush(new RespArray(array));
    }
}
