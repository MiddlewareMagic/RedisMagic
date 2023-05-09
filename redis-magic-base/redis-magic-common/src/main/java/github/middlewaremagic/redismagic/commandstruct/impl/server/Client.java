package github.middlewaremagic.redismagic.commandstruct.impl.server;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.SimpleString;
import github.middlewaremagic.redismagic.utils.TRACEID;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public void handle(ChannelHandlerContext ctx, ICache redisCore) {
        // 任务Id 与 服务器名称 暂不做实际处理
        String traceId = TRACEID.currentTraceId();
        log.debug("traceId:{} 当前的子命令是：{}" + traceId + subCommand);
//        switch (subCommand) {
//            case "setname":
//                BytesWrapper connectionName = ((BulkString) array[2]).getContent();
//                redisCore.putClient(connectionName, ctx.channel());
//                break;
//            default:
//                throw new IllegalArgumentException();
//        }
        ctx.writeAndFlush(new SimpleString("OK"));
    }
}
