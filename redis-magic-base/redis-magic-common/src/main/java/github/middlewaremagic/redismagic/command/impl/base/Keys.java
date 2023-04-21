package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Keys
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

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Keys implements Command {
    String pattern = "";

    @Override
    public CommandType type() {
        return CommandType.keys;
    }

    @Override
    public void setContent(Resp[] array) {
        //需要转译的字符(    [     {    /    ^    -    $     ¦    }    ]    )    ?    *    +    .
        pattern = "." + ((BulkString) array[1]).getContent().toUtf8String();

    }


    @Override
    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
        Set<BytesWrapper> keySet = redisCore.keys();

        Resp[] resps = keySet.stream().filter(k -> {
            String content = null;
            try {
                content = k.toUtf8String();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return Pattern.matches(pattern, content);
        }).flatMap(key -> {
            Resp[] info = new Resp[1];
            info[0] = new BulkString(key);
            return Stream.of(info);
        }).toArray(Resp[]::new);
        ctx.writeAndFlush(new RespArray(resps));
    }
}
