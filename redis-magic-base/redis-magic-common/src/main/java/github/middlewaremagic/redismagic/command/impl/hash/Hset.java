package github.middlewaremagic.redismagic.command.impl.hash;/*
 * ClassName: Hset
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;
import github.middlewaremagic.redismagic.datatype.RedisHash;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespInt;
import io.netty.channel.ChannelHandlerContext;

public class Hset implements WriteCommand {
    private BytesWrapper key;
    private BytesWrapper field;
    private BytesWrapper value;

    @Override
    public CommandType type() {
        return CommandType.hset;
    }

    @Override
    public void setContent(Resp[] array) {
        key = ((BulkString) array[1]).getContent();
        field = ((BulkString) array[2]).getContent();
        value = ((BulkString) array[3]).getContent();
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            RedisHash redisHash = new RedisHash();
            int put = redisHash.put(field, value);
            redisCore.put(key, redisHash);
            ctx.writeAndFlush(new RespInt(put));
        } else if (redisData instanceof RedisHash) {
            RedisHash redisHash = (RedisHash) redisData;
            int put = redisHash.put(field, value);
            ctx.writeAndFlush(new RespInt(put));
        } else {
            throw new IllegalArgumentException("类型错误");
        }
    }

    @Override
    public void handle(RedisCore redisCore) {
        RedisData redisData = redisCore.get(key);
        if (redisData == null) {
            RedisHash redisHash = new RedisHash();
            redisHash.put(field, value);
            redisCore.put(key, redisHash);
        } else if (redisData instanceof RedisHash) {
            RedisHash redisHash = (RedisHash) redisData;
            redisHash.put(field, value);
        } else {
            throw new IllegalArgumentException("类型错误");
        }
    }
}
