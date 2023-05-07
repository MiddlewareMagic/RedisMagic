package github.middlewaremagic.redismagic.commandstruct.impl.connection;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.SimpleString;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Auth implements Command {
    private String password;

    @Override
    public CommandType type() {
        return CommandType.auth;
    }

    @Override
    public void setContent(Resp[] array) {
        BulkString blukStrings = (BulkString) array[1];
        byte[] content = blukStrings.getContent().getByteArray();
        if (content.length == 0) {
            password = "";
        } else {
            password = new String(content);
        }
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        SimpleString ok = new SimpleString("OK");
        ctx.writeAndFlush(ok);
    }
}
