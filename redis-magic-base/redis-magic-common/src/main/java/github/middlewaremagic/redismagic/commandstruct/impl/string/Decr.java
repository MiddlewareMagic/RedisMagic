package github.middlewaremagic.redismagic.commandstruct.impl.string;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.SimpleString;
import github.middlewaremagic.redismagic.utils.type.Format;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class Decr implements WriteCommand {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.decr;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache iCache) {
        Object obj = iCache.get(key);
        if(!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", iCache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            RedisString stringData = new RedisString();
            BytesWrapper bytesWrapper = new BytesWrapper("0".getBytes(UTF_8));
            stringData.setValue(bytesWrapper);
            iCache.put(key, stringData);
            ctx.writeAndFlush(new BulkString(bytesWrapper));
        } else if (redisData instanceof RedisString) {
            try {
                BytesWrapper value = ((RedisString) redisData).getValue();
                long v = Format.parseLong(value.getByteArray(), 10);
                --v;
                BytesWrapper bytesWrapper = new BytesWrapper(Format.toByteArray(v));
                ((RedisString) redisData).setValue(bytesWrapper);
                ctx.writeAndFlush(new BulkString(bytesWrapper));
            } catch (NumberFormatException exception) {
                ctx.writeAndFlush(new SimpleString("value is not an integer or out of range"));
            }

        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void handle(ICache iCache) {
        Object obj = iCache.get(key);
        if(!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", iCache.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            RedisString stringData = new RedisString(new BytesWrapper("0".getBytes(UTF_8)));
            iCache.put(key, stringData);
        } else if (redisData instanceof RedisString) {
            try {
                BytesWrapper value = ((RedisString) redisData).getValue();
                long v = Format.parseLong(value.getByteArray(), 10);
                --v;
                ((RedisString) redisData).setValue(new BytesWrapper(Format.toByteArray(v)));
            } catch (NumberFormatException exception) {
                log.error("");
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
