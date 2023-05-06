//package github.middlewaremagic.redismagic.command.impl.key;
//
//import github.middlewaremagic.redismagic.command.CommandType;
//import github.middlewaremagic.redismagic.command.WriteCommand;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class Del implements WriteCommand {
//    private List<BytesWrapper> keys;
//
//    @Override
//    public CommandType type() {
//        return CommandType.del;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        keys = Stream.of(array).skip(1).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        long remove = redisCore.remove(keys);
//        ctx.writeAndFlush(new RespInt((int) remove));
//    }
//
//    @Override
//    public void handle(RedisCore redisCore) {
//        redisCore.remove(keys);
//    }
//}
