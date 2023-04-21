package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Push
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;
import github.middlewaremagic.redismagic.datatype.RedisList;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.Errors;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public abstract class Push implements WriteCommand {
    BiConsumer<RedisList, List<BytesWrapper>> biConsumer;
    private BytesWrapper key;
    private List<BytesWrapper> value;

    public Push(BiConsumer<RedisList, List<BytesWrapper>> biConsumer) {
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
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
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
    public void handle(RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
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

