package github.middlewaremagic.redismagic.commandstruct.impl.key;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.respstruct.BulkString;
import github.middlewaremagic.redismagic.respstruct.Resp;
import github.middlewaremagic.redismagic.respstruct.RespArray;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
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
    public void handle(ChannelHandlerContext ctx, ICache cache) {
        Set<BytesWrapper> keySet = cache.keySet();
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