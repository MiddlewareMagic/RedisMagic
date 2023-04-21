package github.middlewaremagic.redismagic;/*
 * ClassName: CommandDecoder
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

import github.middlewaremagic.redismagic.aof.Aof;
import github.middlewaremagic.redismagic.command.Command;
import github.middlewaremagic.redismagic.command.CommandFactory;
import github.middlewaremagic.redismagic.command.WriteCommand;
import github.middlewaremagic.redismagic.resp.BulkString;
import github.middlewaremagic.redismagic.resp.Resp;
import github.middlewaremagic.redismagic.resp.Errors;
import github.middlewaremagic.redismagic.resp.RespArray;
import github.middlewaremagic.redismagic.resp.SimpleString;
import github.middlewaremagic.redismagic.util.TRACEID;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.log4j.Logger;

public class CommandDecoder extends LengthFieldBasedFrameDecoder {
    private static final Logger LOGGER = Logger.getLogger(CommandDecoder.class);
    private static final int MAX_FRAME_LENGTH = Integer.MAX_VALUE;
    private Aof aof = null;

    //    static {
//        if(PropertiesUtil.getAppendOnly()) {
//            aof=new Aof();
//        }
//    }
    public CommandDecoder(Aof aof) {
        this();
        this.aof = aof;
    }

    public CommandDecoder() {
        super(MAX_FRAME_LENGTH, 0, 4);
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        TRACEID.newTraceId();
        while (in.readableBytes() != 0) {
            int mark = in.readerIndex();
            try {
                Resp resp = Resp.decode(in);
                if (!(resp instanceof RespArray || resp instanceof SimpleString)) {
                    throw new IllegalStateException("客户端发送的命令应该只能是Resp Array 和 单行命令 类型");
                }
                Command command = null;
                if (resp instanceof RespArray) {
                    command = CommandFactory.from((RespArray) resp);
                } else if (resp instanceof SimpleString) {
                    command = CommandFactory.from((SimpleString) resp);

                }
                if (command == null) {
                    //取出命令
                    ctx.writeAndFlush(new Errors("unsupport command:" + ((BulkString) ((RespArray) resp).getArray()[0]).getContent().toUtf8String()));
                } else {
                    if (aof != null && command instanceof WriteCommand) {
                        aof.put(resp);
                    }
                    return command;
                }
            } catch (Exception e) {
                in.readerIndex(mark);
                LOGGER.error("解码命令", e);
                break;
            }
        }
        return null;
    }


}

