package github.middlewaremagic.redismagic.command;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.commandstruct.Command;
import github.middlewaremagic.redismagic.utils.TRACEID;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: `redisRewrite
 * @description: Command 处理操作类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-07 21:39
 **/
@Slf4j
public class CommandHandler extends SimpleChannelInboundHandler<Command> {

    private final ICache iCache;

    public CommandHandler(ICache iCache)
    {
        this.iCache = iCache;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Command command) throws Exception {
        String traceId = TRACEID.currentTraceId();
        log.debug("traceId:"+ traceId+" 本次处理的命令："+command.type().name());
        try{
            command.handle(ctx, iCache);
        }catch(Exception e){
            log.error("处理数据时",e);
        }
        log.debug("traceId:"+traceId+" 命令处理完毕");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(" ExceptionCaught：",cause);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        ctx.close();
    }

}
