//package github.middlewaremagic.redismagic.command.impl.string;
//
//import github.middlewaremagic.redismagic.command.CommandType;
//import github.middlewaremagic.redismagic.command.WriteCommand;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.RedisData;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisString;
//import io.netty.channel.ChannelHandlerContext;
//
//import static java.nio.charset.StandardCharsets.UTF_8;
//
//public class Decr implements WriteCommand {
//    private BytesWrapper key;
//
//    @Override
//    public CommandType type() {
//        return CommandType.decr;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//    }
//
//    @Override
//    public void handle(ChannelHandlerContext ctx, RedisCore redisCore) {
//        RedisData redisData = redisCore.get(key);
//        if (redisData == null) {
//            RedisString stringData = new RedisString();
//            BytesWrapper bytesWrapper = new BytesWrapper("0".getBytes(UTF_8));
//            stringData.setValue(bytesWrapper);
//            redisCore.put(key, stringData);
//            ctx.writeAndFlush(new BulkString(bytesWrapper));
//        } else if (redisData instanceof RedisString) {
//            try {
//                BytesWrapper value = ((RedisString) redisData).getValue();
//                long v = Format.parseLong(value.getByteArray(), 10);
//                --v;
//                BytesWrapper bytesWrapper = new BytesWrapper(Format.toByteArray(v));
//                ((RedisString) redisData).setValue(bytesWrapper);
//                ctx.writeAndFlush(new BulkString(bytesWrapper));
//            } catch (NumberFormatException exception) {
//                ctx.writeAndFlush(new SimpleString("value is not an integer or out of range"));
//            }
//
//        } else {
//            throw new UnsupportedOperationException();
//        }
//    }
//
//    @Override
//    public void handle(RedisCore redisCore) {
//        RedisData redisData = redisCore.get(key);
//        if (redisData == null) {
//            RedisString stringData = new RedisString(new BytesWrapper("0".getBytes(UTF_8)));
//            redisCore.put(key, stringData);
//        } else if (redisData instanceof RedisString) {
//            try {
//                BytesWrapper value = ((RedisString) redisData).getValue();
//                long v = Format.parseLong(value.getByteArray(), 10);
//                --v;
//                ((RedisString) redisData).setValue(new BytesWrapper(Format.toByteArray(v)));
//            } catch (NumberFormatException exception) {
//            }
//        } else {
//            throw new UnsupportedOperationException();
//        }
//    }
//}
