package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Client
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.SimpleString;
import github.middlewaremagic.redismagic.util.TRACEID;
import io.netty.channel.ChannelHandlerContext;

public class Client implements Command {
    private String subCommand;
    private Resp[] array;

    @Override
    public CommandType type() {
        return CommandType.client;
    }

    @Override
    public void setContent(Resp[] array) {
        this.array = array;
        subCommand = ((BulkString) array[1]).getContent().toUtf8String();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        String traceId = TRACEID.currentTraceId();
        LOGGER.debug("traceId:{} 当前的子命令是：{}" + traceId + subCommand);
        switch (subCommand) {
            case "setname":
                BytesWrapper connectionName = ((BulkString) array[2]).getContent();
                redisCore.putClient(connectionName, ctx.channel());
                break;
            default:
                throw new IllegalArgumentException();
        }
        ctx.writeAndFlush(new SimpleString("OK"));
    }
}
