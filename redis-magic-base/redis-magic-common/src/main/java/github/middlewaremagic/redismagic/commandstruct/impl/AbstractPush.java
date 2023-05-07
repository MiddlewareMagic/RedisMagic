package github.middlewaremagic.redismagic.commandstruct.impl;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisList;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Errors;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespInt;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
public abstract class AbstractPush implements WriteCommand {

    /**
     * 看起来是 aof 实现
     */

    BiConsumer<RedisList, List<BytesWrapper>> biConsumer;
    private BytesWrapper key;
    private List<BytesWrapper> value;

    public AbstractPush(BiConsumer<RedisList, List<BytesWrapper>> biConsumer) {
        this.biConsumer = biConsumer;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        value = new ArrayList<>();
        for (int i = 2; i < array.length; i++) {
            value.add(((BulkString) array[i]).getContent());
        }
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache redisCore) {

        Object obj = redisCore.get(key);
        if(!(obj instanceof RedisData)) {
            log.error("ICache type error. Please check out. {}", redisCore.toString());
            return;
        }
        RedisData redisData = (RedisData) redisCore.get(key);
        if (redisData == null) {
            RedisList redisList = new RedisList();
            biConsumer.accept(redisList, value);
            redisCore.put(key, redisList);
            ctx.writeAndFlush(new RespInt(redisList.size()));
        } else if (redisData != null && !(redisData instanceof RedisList)) {
            ctx.writeAndFlush(new Errors("wrong type"));
        } else {
            biConsumer.accept((RedisList) redisData, value);
            redisCore.put(key, redisData);
            ctx.writeAndFlush(new RespInt(((RedisList) redisData).size()));
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
            RedisList redisList = new RedisList();
            biConsumer.accept(redisList, value);
            redisCore.put(key, redisList);

        } else if (redisData != null && !(redisData instanceof RedisList)) {
        } else {
            biConsumer.accept((RedisList) redisData, value);
            redisCore.put(key, redisData);
        }
    }
}
