//package github.middlewaremagic.redismagic.command.impl.key;
//
//import github.middlewaremagic.redismagic.command.Command;
//import github.middlewaremagic.redismagic.command.CommandType;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.stream.Collectors;
//
//public class Scan implements Command {
//    @Override
//    public CommandType type() {
//        return CommandType.scan;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        Resp[] array = new Resp[2];
//        BulkString blukStrings = new BulkString(new BytesWrapper("0".getBytes(CHARSET)));
//        array[0] = blukStrings;
//        List<BulkString> collect = redisCore.keys().stream().map(keyName -> new BulkString(keyName)).collect(Collectors.toList());
//        array[1] = new RespArray(collect.toArray(new Resp[collect.size()]));
//        ctx.writeAndFlush(new RespArray(array));
//    }
//}
