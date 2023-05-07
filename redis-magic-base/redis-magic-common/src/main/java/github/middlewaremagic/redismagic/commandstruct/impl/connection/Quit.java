//package github.middlewaremagic.redismagic.commandstruct.impl.connection;
//
//import github.middlewaremagic.redismagic.commandstruct.Command;
//import github.middlewaremagic.redismagic.commandstruct.CommandType;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import io.netty.channel.ChannelHandlerContext;

//public class Quit implements Command {
//    @Override
//    public CommandType type() {
//        return CommandType.quit;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        ctx.writeAndFlush(SimpleString.OK);
//        ctx.close();
//    }
//}
