//package github.middlewaremagic.redismagic.commandstruct.impl.list;
//
//import github.middlewaremagic.redismagic.commandstruct.Command;
//import github.middlewaremagic.redismagic.commandstruct.CommandType;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisList;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.List;
//
//public class Lrange implements Command {
//    BytesWrapper key;
//    int start;
//    int end;
//
//    @Override
//    public CommandType type() {
//        return CommandType.lrange;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//        start = Integer.parseInt(((BulkString) array[2]).getContent().toUtf8String());
//        end = Integer.parseInt(((BulkString) array[3]).getContent().toUtf8String());
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        RedisList redisList = (RedisList) redisCore.get(key);
//        List<BytesWrapper> lrang = redisList.lrang(start, end);
//        RespArray respArray = new RespArray(lrang.stream().map(BulkString::new).toArray(Resp[]::new));
//        ctx.writeAndFlush(respArray);
//    }
//}
