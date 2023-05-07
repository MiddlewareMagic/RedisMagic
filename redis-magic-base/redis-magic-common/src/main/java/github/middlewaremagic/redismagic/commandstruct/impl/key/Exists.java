package github.middlewaremagic.redismagic.commandstruct.impl.key;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Exists implements Command {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.exists;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        boolean exist = cache.containsKey(key);
        if (exist) {
            ctx.writeAndFlush(new RespInt(1));
        } else {
            ctx.writeAndFlush(new RespInt(0));
        }
    }
}
