package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: AbstraceScan
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespArray;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstraceScan implements Command {
    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        Resp[] array = new Resp[2];
        BulkString blukStrings = new BulkString(new BytesWrapper("0".getBytes(CHARSET)));
        array[0] = blukStrings;
        array[1] = get(redisCore);
        ctx.writeAndFlush(new RespArray(array));
    }

    protected abstract RespArray get(RedisCore redisCore);
}
