//package github.middlewaremagic.redismagic.command.impl.string;
//
//import github.middlewaremagic.redismagic.command.Command;
//import github.middlewaremagic.redismagic.command.CommandType;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.RedisData;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class Mget implements Command {
//    private List<BytesWrapper> keys;
//
//    @Override
//    public CommandType type() {
//        return CommandType.mget;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        keys = Stream.of(array).skip(1).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        LinkedList<BytesWrapper> linkedList = new LinkedList();
//        keys.forEach(key -> {
//            RedisData redisData = redisCore.get(key);
//            if (redisData == null) {
//            } else if (redisData instanceof RedisString) {
//                linkedList.add(((RedisString) redisData).getValue());
//            } else {
//                throw new UnsupportedOperationException();
//            }
//        });
//        RespArray respArray = new RespArray(linkedList.stream().map(BulkString::new).toArray(Resp[]::new));
//        ctx.writeAndFlush(respArray);
//    }
//
//}
