package github.middlewaremagic.redismagic.commandstruct.impl;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespArray;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractScan implements Command {

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        Resp[] array = new Resp[2];
        BulkString blukStrings = new BulkString(new BytesWrapper("0".getBytes(CHARSET)));
        array[0] = blukStrings;
        array[1] = get(cache);
        ctx.writeAndFlush(new RespArray(array));
    }

    protected abstract RespArray get(ICache redisCore);
}
