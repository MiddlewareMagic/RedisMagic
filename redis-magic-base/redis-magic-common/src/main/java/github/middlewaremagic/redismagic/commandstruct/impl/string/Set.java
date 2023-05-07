package github.middlewaremagic.redismagic.commandstruct.impl.string;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.WriteCommand;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.SimpleString;
import io.netty.channel.ChannelHandlerContext;

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
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        value = ((BulkString) array[2]).getContent();
        int index = 3;
        while (index < array.length) {
            String string = ((BulkString) array[index]).getContent().toUtf8String();
            index++;
            if (string.startsWith("EX")) {
                String seconds = ((BulkString) array[index]).getContent().toUtf8String();
                timeout = Integer.parseInt(seconds) * 1000;
            } else if (string.startsWith("PX")) {
                String seconds = ((BulkString) array[index]).getContent().toUtf8String();
                timeout = Integer.parseInt(seconds);
            } else if (string.equals("NX")) {
                notExistSet = true;
            } else if (string.equals("XX")) {
                existSet = true;
            }
        }
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ICache iCache) {
        if (notExistSet && iCache.containsKey(key)) {
            ctx.writeAndFlush(BulkString.NullBulkString);
        } else if (existSet && !iCache.containsKey(key)) {
            ctx.writeAndFlush(BulkString.NullBulkString);
        } else {
            if (timeout != -1) {
                timeout += System.currentTimeMillis();
            }
            RedisString stringData = new RedisString();
            stringData.setValue(value);
            iCache.put(key, stringData);
            iCache.expireAt(key, timeout);
            ctx.writeAndFlush(new SimpleString("OK"));
        }
    }

    @Override
    public void handle(ICache iCache) {
        if (notExistSet && iCache.containsKey(key)) {

        } else if (existSet && !iCache.containsKey(key)) {

        } else {
            if (timeout != -1) {
                timeout += System.currentTimeMillis();
            }
            RedisString stringData = new RedisString();
            stringData.setValue(value);
            iCache.put(key, stringData);
            iCache.expireAt(key, timeout);
        }
    }
}
