package github.middlewaremagic.redismagic.commandstruct.impl.connection;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.SimpleString;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Quit implements Command {
    @Override
    public CommandType type() {
        return CommandType.quit;
    }

    @Override
    public void setContent(Resp[] array) {
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        ctx.writeAndFlush(SimpleString.OK);
        ctx.close();
    }
}
