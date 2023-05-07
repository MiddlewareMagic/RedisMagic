//package github.middlewaremagic.redismagic.commandstruct.impl.set;
//
//import github.middlewaremagic.redismagic.commandstruct.CommandType;
//import github.middlewaremagic.redismagic.core.RedisCore;
//import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
//import github.middlewaremagic.redismagic.datastruct.impl.RedisSet;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Sscan extends AbstraceScan {
//    private BytesWrapper key;
//
//    @Override
//    public CommandType type() {
//        return CommandType.sscan;
//    }
//
//    @Override
//    public void setContent(Resp[] array) {
//        key = ((BulkString) array[1]).getContent();
//    }
//
//    @Override
//    protected RespArray get(RedisCore redisCore) {
//        RedisSet redisSet = (RedisSet) redisCore.get(key);
//        List<BulkString> collect = redisSet.keys().stream().map(keyName -> new BulkString(keyName)).collect(Collectors.toList());
//        return new RespArray(collect.toArray(new Resp[collect.size()]));
//    }
//}
