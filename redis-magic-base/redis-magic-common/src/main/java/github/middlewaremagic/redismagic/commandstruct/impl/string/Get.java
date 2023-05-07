package github.middlewaremagic.redismagic.commandstruct.impl.string;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Get implements Command {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.get;
    }

    @Override
    public void setContent(List<String> commandList) {
        key = new BytesWrapper(commandList.get(1).getBytes());
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        Object obj = cache.get(key);
        if (!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", cache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            ctx.writeAndFlush(BulkString.NullBulkString);
        } else if (redisData instanceof RedisString) {
            BytesWrapper value = ((RedisString) redisData).getValue();
            ctx.writeAndFlush(new BulkString(value));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void handle(ICache cache) {
        Object obj = cache.get(key);
        if (!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", cache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {

        } else if (redisData instanceof RedisString) {
            BytesWrapper value = ((RedisString) redisData).getValue();
            String res = value.toUtf8String();
            System.out.println(res);
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
