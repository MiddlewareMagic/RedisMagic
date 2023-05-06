package github.middlewaremagic.redismagic.command;/*
 * ClassName: Command
 * Description:
 * @Author: zjh
 * @Create: 2023/5/5
 */

import github.middlewaremagic.redismagic.core.RedisCore;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public interface Command {
    Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * 获取命令类型
     *
     * @return 接口类型
     */
    CommandType type();

    /**
     * 设置内容
     *
     * @param commandList
     */
    void setContent(List<String> commandList);

    /**
     * 处理消息命令
     *
     * @param ctx       管道
     * @param redisCore redis数据库
     */
//    void handle(ChannelHandlerContext ctx, RedisCore redisCore);

    /**
     * 处理消息命令
     *
     * @param redisCore
     */
    void handle(RedisCore redisCore);

}
