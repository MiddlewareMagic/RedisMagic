//package github.middlewaremagic.redismagic.commandstruct.impl.hash;
//
//import github.middlewaremagic.redismagic.commandstruct.CommandType;
//import github.middlewaremagic.redismagic.commandstruct.impl.AbstractScan;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisHash;
//
//import java.util.Map;
//import java.util.stream.Stream;

//public class Hscan extends AbstractScan {
//    private BytesWrapper key;
//
//    @Override
//    public CommandType type() {
//        return CommandType.hscan;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//    }
//
//    @Override
//    protected RespArray get(RedisCore redisCore) {
//        RedisHash redisHash = (RedisHash) redisCore.get(key);
//        Map<BytesWrapper, BytesWrapper> map = redisHash.getMap();
//        return new RespArray(map.entrySet().stream().flatMap(entry -> {
//            Resp[] resps = new Resp[2];
//            resps[0] = new BulkString(entry.getKey());
//            resps[1] = new BulkString(entry.getValue());
//            return Stream.of(resps);
//        }).toArray(Resp[]::new));
//    }
//}
