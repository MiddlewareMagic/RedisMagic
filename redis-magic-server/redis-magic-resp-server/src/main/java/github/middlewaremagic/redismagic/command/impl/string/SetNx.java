//package github.middlewaremagic.redismagic.command.impl.string;
//
//import github.middlewaremagic.redismagic.command.CommandType;
//import github.middlewaremagic.redismagic.command.WriteCommand;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
//import io.netty.channel.ChannelHandlerContext;
//
//public class SetNx implements WriteCommand {
//    private BytesWrapper key;
//    private BytesWrapper value;
//
//    @Override
//    public CommandType type() {
//        return CommandType.setnx;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//        value = ((BulkString) array[2]).getContent();
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        boolean exist = redisCore.exist(key);
//        if (exist) {
//            ctx.writeAndFlush(new RespInt(0));
//        } else {
//            RedisString redisString = new RedisString();
//            redisString.setValue(value);
//            redisCore.put(key, redisString);
//            ctx.writeAndFlush(new RespInt(1));
//        }
//    }
//
//    @Override
//    public void handle(RedisCore redisCore) {
//        boolean exist = redisCore.exist(key);
//        if (exist) {
//        } else {
//            RedisString redisString = new RedisString();
//            redisString.setValue(value);
//            redisCore.put(key, redisString);
//
//        }
//    }
//}
