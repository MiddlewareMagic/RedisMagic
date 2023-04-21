package github.middlewaremagic.redismagic.command.impl.set;/*
 * ClassName: Scan
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.RespArray;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.stream.Collectors;

public class Scan implements Command {
    @Override
    public CommandType type() {
        return CommandType.scan;
    }

    @Override
    public void setContent(Resp[] array) {
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        Resp[] array = new Resp[2];
        BulkString blukStrings = new BulkString(new BytesWrapper("0".getBytes(CHARSET)));
        array[0] = blukStrings;
        List<BulkString> collect = redisCore.keys().stream().map(keyName -> new BulkString(keyName)).collect(Collectors.toList());
        array[1] = new RespArray(collect.toArray(new Resp[collect.size()]));
        ctx.writeAndFlush(new RespArray(array));
    }
}