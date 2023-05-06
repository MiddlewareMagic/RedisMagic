package github.middlewaremagic.redismagic.command.impl.string;

import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.core.RedisCore;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class Set implements WriteCommand {
    private BytesWrapper key;
    private BytesWrapper value;
    private long timeout = -1;
    private boolean notExistSet = false;
    private boolean existSet = false;

    @Override
    public CommandType type() {
        return CommandType.set;
    }

    @Override
    public void setContent(List<String> commandList) {
        key = new BytesWrapper(commandList.get(1).getBytes());
        value = new BytesWrapper(commandList.get(2).getBytes());
        int index = 3;
        while (index < commandList.size()) {
            String string = commandList.get(index);
            index++;
            if (string.startsWith("EX")) {
                String seconds = commandList.get(index);
                timeout = Integer.parseInt(seconds) * 1000;
            } else if (string.startsWith("PX")) {
                String seconds = commandList.get(index);
                timeout = Integer.parseInt(seconds);
            } else if (string.equals("NX")) {
                notExistSet = true;
            } else if (string.equals("XX")) {
                existSet = true;
            }
        }
    }

//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        if (notExistSet && redisCore.exist(key)) {
//            ctx.writeAndFlush(BulkString.NullBulkString);
//        } else if (existSet && !redisCore.exist(key)) {
//            ctx.writeAndFlush(BulkString.NullBulkString);
//        } else {
//            if (timeout != -1) {
//                timeout += System.currentTimeMillis();
//            }
//            RedisString stringData = new RedisString();
//            stringData.setValue(value);
//            stringData.setTimeout(timeout);
//            redisCore.put(key, stringData);
//            ctx.writeAndFlush(new SimpleString("OK"));
//        }
//    }

    @Override
    public void handle(RedisCore redisCore) {
        if (notExistSet && redisCore.exist(key)) {

        } else if (existSet && !redisCore.exist(key)) {

        } else {
            if (timeout != -1) {
                timeout += System.currentTimeMillis();
            }
            RedisString stringData = new RedisString();
            stringData.setValue(value);
//            stringData.setTimeout(timeout);
            redisCore.put(key, stringData);
        }
    }
}
