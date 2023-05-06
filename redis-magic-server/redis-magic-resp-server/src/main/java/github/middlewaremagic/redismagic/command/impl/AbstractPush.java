//package github.middlewaremagic.redismagic.command.impl;
//
//import github.middlewaremagic.redismagic.command.WriteCommand;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.RedisData;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisList;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.unix.Errors;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.BiConsumer;
//
//public abstract class AbstractPush implements WriteCommand {
//    BiConsumer<RedisList, List<BytesWrapper>> biConsumer;
//    private BytesWrapper key;
//    private List<BytesWrapper> value;
//
//    public AbstractPush(BiConsumer<RedisList, List<BytesWrapper>> biConsumer) {
//        this.biConsumer = biConsumer;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//        value = new ArrayList<>();
//        for (int i = 2; i < array.length; i++) {
//            value.add(((BulkString) array[i]).getContent());
//        }
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        RedisData redisData = redisCore.get(key);
//        if (redisData == null) {
//            RedisList redisList = new RedisList();
//            biConsumer.accept(redisList, value);
//            redisCore.put(key, redisList);
//            ctx.writeAndFlush(new RespInt(redisList.size()));
//        } else if (redisData != null && !(redisData instanceof RedisList)) {
//            ctx.writeAndFlush(new Errors("wrong type"));
//        } else {
//            biConsumer.accept((RedisList) redisData, value);
//            redisCore.put(key, redisData);
//            ctx.writeAndFlush(new RespInt(((RedisList) redisData).size()));
//        }
//    }
//
//    @Override
//    public void handle(RedisCore redisCore) {
//        RedisData redisData = redisCore.get(key);
//        if (redisData == null) {
//            RedisList redisList = new RedisList();
//            biConsumer.accept(redisList, value);
//            redisCore.put(key, redisList);
//
//        } else if (redisData != null && !(redisData instanceof RedisList)) {
//        } else {
//            biConsumer.accept((RedisList) redisData, value);
//            redisCore.put(key, redisData);
//        }
//    }
//}
