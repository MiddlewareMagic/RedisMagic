package github.middlewaremagic.redismagic.commandstruct.impl.server;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespArray;
import github.middlewaremagic.redismagic.utils.TRACEID;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Config implements Command {
    private String param;

    @Override
    public CommandType type() {
        return CommandType.config;
    }

    @Override
    public void setContent(Resp[] array) {
        if (array.length != 3) {
            throw new IllegalStateException();
        }
        if (((BulkString) array[1]).getContent().toUtf8String().equals("get") == false) {
            throw new IllegalStateException();
        }
        param = ((BulkString) array[2]).getContent().toUtf8String();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        if (param.equals("*") || param.equals("databases")) {
            List<BulkString> list = new ArrayList<>();
            list.add(new BulkString(new BytesWrapper("databases".getBytes(CHARSET))));
            list.add(new BulkString(new BytesWrapper("1".getBytes(CHARSET))));
            Resp[] array = list.toArray(new Resp[list.size()]);
            RespArray arrays = new RespArray(array);
            ctx.writeAndFlush(arrays);
        } else {
            String traceId = TRACEID.currentTraceId();
            log.debug("traceId:" + traceId + " 不识别的Config命令模式:" + param);
            ctx.channel().close();
        }
    }
}
