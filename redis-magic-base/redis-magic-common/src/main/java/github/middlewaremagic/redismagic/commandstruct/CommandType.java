package github.middlewaremagic.redismagic.commandstruct;/*
 * ClassName: CommandType
 * Description:
 * @Author: zjh
 * @Create: 2023/5/5
 */
import github.middlewaremagic.redismagic.commandstruct.impl.connection.Auth;
import github.middlewaremagic.redismagic.commandstruct.impl.connection.Ping;
import github.middlewaremagic.redismagic.commandstruct.impl.connection.Quit;
import github.middlewaremagic.redismagic.commandstruct.impl.connection.Select;
import github.middlewaremagic.redismagic.commandstruct.impl.hash.Hdel;
import github.middlewaremagic.redismagic.commandstruct.impl.hash.Hscan;
import github.middlewaremagic.redismagic.commandstruct.impl.hash.Hset;
import github.middlewaremagic.redismagic.commandstruct.impl.key.*;
import github.middlewaremagic.redismagic.commandstruct.impl.list.Lpush;
import github.middlewaremagic.redismagic.commandstruct.impl.list.Lrange;
import github.middlewaremagic.redismagic.commandstruct.impl.list.Lrem;
import github.middlewaremagic.redismagic.commandstruct.impl.list.Rpush;
import github.middlewaremagic.redismagic.commandstruct.impl.server.Client;
import github.middlewaremagic.redismagic.commandstruct.impl.server.Config;
import github.middlewaremagic.redismagic.commandstruct.impl.server.Info;
import github.middlewaremagic.redismagic.commandstruct.impl.set.Sadd;
import github.middlewaremagic.redismagic.commandstruct.impl.set.Srem;
import github.middlewaremagic.redismagic.commandstruct.impl.set.Sscan;
import github.middlewaremagic.redismagic.commandstruct.impl.string.*;
import github.middlewaremagic.redismagic.commandstruct.impl.zset.Zadd;
import github.middlewaremagic.redismagic.commandstruct.impl.zset.Zrem;
import github.middlewaremagic.redismagic.commandstruct.impl.zset.Zrevrange;
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
