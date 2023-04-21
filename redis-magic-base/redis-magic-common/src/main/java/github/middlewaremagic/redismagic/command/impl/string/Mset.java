package github.middlewaremagic.redismagic.command.impl.string;/*
 * ClassName: Mset
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.RedisCore;
import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisString;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.SimpleString;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mset implements WriteCommand {
    private List<BytesWrapper> kvList;

    @Override
    public CommandType type() {
        return CommandType.mset;
    }

    @Override
    public void setContent(Resp[] array) {
        kvList = Stream.of(array).skip(1).map(resp -> ((BulkString) resp).getContent()).collect(Collectors.toList());
    }

    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        for (int i = 0; i < kvList.size(); i += 2) {

            redisCore.put(kvList.get(i), new RedisString(kvList.get(i + 1)));
        }
        ctx.writeAndFlush(SimpleString.OK);
    }

    @Override
    public void handle(RedisCore redisCore) {
        for (int i = 0; i < kvList.size(); i += 2) {
            redisCore.put(kvList.get(i), new RedisString(kvList.get(i + 1)));
        }
    }
}

