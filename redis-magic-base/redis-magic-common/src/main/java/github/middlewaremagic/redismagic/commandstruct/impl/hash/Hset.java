package github.middlewaremagic.redismagic.commandstruct.impl.hash;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisHash;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hset implements WriteCommand {
    private BytesWrapper key;
    private BytesWrapper field;
    private BytesWrapper value;

    @Override
    public CommandType type() {
        return CommandType.hset;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        field = ((BulkString) array[2]).getContent();
        value = ((BulkString) array[3]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache redisCore) {
        Object obj = redisCore.get(key);
        if(!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", redisCore.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            RedisHash redisHash = new RedisHash();
            int put = redisHash.put(field, value);
            redisCore.put(key, redisHash);
            ctx.writeAndFlush(new RespInt(put));
        } else if (redisData instanceof RedisHash) {
            RedisHash redisHash = (RedisHash) redisData;
            int put = redisHash.put(field, value);
            ctx.writeAndFlush(new RespInt(put));
        } else {
            throw new IllegalArgumentException("类型错误");
        }
    }

    @Override
    public void handle(ICache redisCore) {
        Object obj = redisCore.get(key);
        if(!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", redisCore.toString());
            return;
        }
        RedisData redisData = (RedisData) obj;
        if (redisData == null) {
            RedisHash redisHash = new RedisHash();
            redisHash.put(field, value);
            redisCore.put(key, redisHash);
        } else if (redisData instanceof RedisHash) {
            RedisHash redisHash = (RedisHash) redisData;
            redisHash.put(field, value);
        } else {
            throw new IllegalArgumentException("类型错误");
        }
    }
}
