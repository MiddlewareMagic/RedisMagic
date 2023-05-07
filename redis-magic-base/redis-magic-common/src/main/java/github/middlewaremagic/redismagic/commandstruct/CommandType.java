package github.middlewaremagic.redismagic.commandstruct;/*
 * ClassName: CommandType
 * Description:
 * @Author: zjh
 * @Create: 2023/5/5
 */

//import github.middlewaremagic.redismagic.commandstruct.impl.connection.*;
//import github.middlewaremagic.redismagic.commandstruct.impl.hash.*;
//import github.middlewaremagic.redismagic.commandstruct.impl.key.*;
//import github.middlewaremagic.redismagic.commandstruct.impl.list.*;
//import github.middlewaremagic.redismagic.commandstruct.impl.server.*;
//import github.middlewaremagic.redismagic.commandstruct.impl.set.*;

import github.middlewaremagic.redismagic.commandstruct.impl.string.*;
//import github.middlewaremagic.redismagic.commandstruct.impl.zset.*;

import java.util.function.Supplier;

public enum CommandType {
    // Connection（连接）
    auth(Auth::new), ping(Ping::new), quit(Quit::new), select(Select::new),
    // Hash（哈希表）
    hdel(Hdel::new), hscan(Hscan::new), hset(Hset::new),
    // Key（键）
    del(Del::new), exists(Exists::new), expire(Expire::new), keys(Keys::new), scan(Scan::new), ttl(Ttl::new), type(Type::new),
    // List（列表）
    lpush(Lpush::new), lrange(Lrange::new), lrem(Lrem::new), rpush(Rpush::new),
    // Server（服务器）
    client(Client::new), config(Config::new), info(Info::new),
    // Set（集合）
    sadd(Sadd::new), srem(Srem::new), sscan(Sscan::new),
    // String（字符串）
    decr(Decr::new), get(Get::new), incr(Incr::new), mget(Mget::new), mset(Mset::new), set(Set::new), setex(SetEx::new), setnx(SetNx::new),
    // SortedSet（有序集合）
    zadd(Zadd::new), zrem(Zrem::new), zrevrange(Zrevrange::new);


    private final Supplier<Command> supplier;

    private CommandType(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier<Command> getSupplier() {
        return supplier;
    }
}
