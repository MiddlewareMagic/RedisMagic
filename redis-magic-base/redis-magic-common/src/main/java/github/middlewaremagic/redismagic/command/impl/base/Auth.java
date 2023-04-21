package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Auth
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.SimpleString;
import io.netty.channel.ChannelHandlerContext;

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
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        SimpleString ok = new SimpleString("OK");
        ctx.writeAndFlush(ok);
    }
}
