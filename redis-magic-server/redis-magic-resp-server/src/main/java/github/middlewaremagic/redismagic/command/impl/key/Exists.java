//package github.middlewaremagic.redismagic.command.impl.key;
//
//import github.middlewaremagic.redismagic.command.Command;
//import github.middlewaremagic.redismagic.command.CommandType;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import io.netty.channel.ChannelHandlerContext;
//
//public class Exists implements Command {
//    private BytesWrapper key;
//
//    @Override
//    public CommandType type() {
//        return CommandType.exists;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        boolean exist = redisCore.exist(key);
//        if (exist) {
//            ctx.writeAndFlush(new RespInt(1));
//        } else {
//            ctx.writeAndFlush(new RespInt(0));
//        }
//    }
//}
