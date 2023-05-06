//package github.middlewaremagic.redismagic.command.impl.list;
//
//import github.middlewaremagic.redismagic.command.CommandType;
//import github.middlewaremagic.redismagic.command.WriteCommand;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisList;
//import io.netty.channel.ChannelHandlerContext;
//
//public class Lrem implements WriteCommand {
//    private BytesWrapper key;
//    private BytesWrapper value;
//
//    @Override
//    public CommandType type() {
//        return CommandType.lrem;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//        value = ((BulkString) array[3]).getContent();
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        RedisList redisList = (RedisList) redisCore.get(key);
//        int remove = redisList.remove(value);
//        ctx.writeAndFlush(new RespInt(remove));
//    }
//
//    @Override
//    public void handle(RedisCore redisCore) {
//        RedisList redisList = (RedisList) redisCore.get(key);
//        redisList.remove(value);
//    }
//}
