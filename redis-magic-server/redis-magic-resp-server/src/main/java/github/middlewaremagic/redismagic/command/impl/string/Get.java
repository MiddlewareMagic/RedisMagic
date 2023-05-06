package github.middlewaremagic.redismagic.command.impl.string;

import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.core.RedisCore;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class Get implements Command {
    private BytesWrapper key;

    @Override
    public CommandType type() {
        return CommandType.get;
    }

    @Override
    public void setContent(List<String> commandList) {
        key = new BytesWrapper(commandList.get(1).getBytes());
    }

//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        RedisData redisData = redisCore.get(key);
//        if (redisData == null) {
//            ctx.writeAndFlush(BulkString.NullBulkString);
//        } else if (redisData instanceof RedisString) {
//            BytesWrapper value = ((RedisString) redisData).getValue();
//            ctx.writeAndFlush(new BulkString(value));
//        } else {
//            throw new UnsupportedOperationException();
//        }
//    }

    @Override
    public void handle(RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {

        } else if (redisData instanceof RedisString) {
            BytesWrapper value = ((RedisString) redisData).getValue();
            String res = value.toUtf8String();
            System.out.println(res);
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
