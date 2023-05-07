package github.middlewaremagic.redismagic.commandstruct;
/*
 * ClassName: Command
 * Description:
 * @Author: zjh
 * @Create: 2023/5/5
 */
import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.respstruct.Resp;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 支持两种模式: 通过 netty 编解码进行操作, 对应 setContent(Resp[]) | handle(ChannelHandlerContent, Cache) 方法
 * 通过方法直接调用, 对应 setContent(List) | handle(Cache)
 */
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
     * @param array 从网络协议获取的命令参数
     */
    void setContent(Resp[] array);

    /**
     * 处理消息命令
     *
     * @param ctx       管道
     * @param redisCore redis数据库
     */
    void handle(ChannelHandlerContext ctx, ICache redisCore);

}
