//package github.middlewaremagic.redismagic.command.impl.set;
//
//import github.middlewaremagic.redismagic.command.CommandType;
//import github.middlewaremagic.redismagic.command.WriteCommand;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisSet;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class Srem implements WriteCommand {
//    private BytesWrapper key;
//    private List<BytesWrapper> members;
//
//    @Override
//    public CommandType type() {
//        return CommandType.srem;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//        members = Stream.of(array).skip(2).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        RedisSet redisSet = (RedisSet) redisCore.get(key);
//        int srem = redisSet.srem(members);
//        ctx.writeAndFlush(new RespInt(srem));
//    }
//
//    @Override
//    public void handle(RedisCore redisCore) {
//        RedisSet redisSet = (RedisSet) redisCore.get(key);
//        redisSet.srem(members);
//    }
//}
