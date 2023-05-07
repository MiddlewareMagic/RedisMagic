//package github.middlewaremagic.redismagic.commandstruct.impl.key;
//
//import github.middlewaremagic.redismagic.commandstruct.CommandType;
//import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.RedisData;
//import io.netty.channel.ChannelHandlerContext;
//
//public class Expire implements WriteCommand {
//    private BytesWrapper key;
//    private int second;
//
//    @Override
//    public CommandType type() {
//        return CommandType.expire;
//    }
//
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//        second = Integer.parseInt(((BulkString) array[2]).getContent().toUtf8String());
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        RedisData redisData = redisCore.get(key);
//        if (redisData == null) {
//            ctx.writeAndFlush(new RespInt(0));
//        } else {
//            redisData.setTimeout(System.currentTimeMillis() + (second * 1000));
//            ctx.writeAndFlush(new RespInt(1));
//        }
//    }
//
//    @Override
//    public void handle(RedisCore redisCore) {
//        RedisData redisData = redisCore.get(key);
//        if (redisData == null) {
//        } else {
//            redisData.setTimeout(System.currentTimeMillis() + (second * 1000));
//        }
//    }
//}
