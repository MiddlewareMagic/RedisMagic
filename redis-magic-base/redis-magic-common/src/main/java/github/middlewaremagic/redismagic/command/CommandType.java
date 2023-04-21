package github.middlewaremagic.redismagic.command;/*
 * ClassName: CommandType
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.command.impl.base.*;
import github.middlewaremagic.redismagic.command.impl.hash.Hdel;
import github.middlewaremagic.redismagic.command.impl.hash.Hscan;
import github.middlewaremagic.redismagic.command.impl.hash.Hset;
import github.middlewaremagic.redismagic.command.impl.list.Lpush;
import github.middlewaremagic.redismagic.command.impl.list.Lrange;
import github.middlewaremagic.redismagic.command.impl.list.Lrem;
import github.middlewaremagic.redismagic.command.impl.set.Sadd;
import github.middlewaremagic.redismagic.command.impl.set.Scan;
import github.middlewaremagic.redismagic.command.impl.set.Srem;
import github.middlewaremagic.redismagic.command.impl.set.Sscan;
import github.middlewaremagic.redismagic.command.impl.string.*;
import github.middlewaremagic.redismagic.command.impl.zset.Zadd;
import github.middlewaremagic.redismagic.command.impl.zset.Zrem;
import github.middlewaremagic.redismagic.command.impl.zset.Zrevrange;

import java.util.function.Supplier;

public enum CommandType {
    auth(Auth::new), config(Config::new), scan(Scan::new),//
    info(Info::new), client(Client::new), set(Set::new), type(Type::new),//
    ttl(Ttl::new), get(Get::new), quit(Quit::new),//
    setnx(SetNx::new), lpush(Lpush::new), lrange(Lrange::new), lrem(Lrem::new), rpush(Rpush::new), del(Del::new), sadd(Sadd::new),//
    sscan(Sscan::new), srem(Srem::new), hset(Hset::new), hscan(Hscan::new), hdel(Hdel::new),//
    zadd(Zadd::new), zrevrange(Zrevrange::new), zrem(Zrem::new), setex(SetEx::new), exists(Exists::new), expire(Expire::new),
    ping(Ping::new), select(Select::new), keys(Keys::new), incr(Incr::new), decr(Decr::new), mset(Mset::new), mget(Mget::new),
    //
    ;

    private final Supplier<Command> supplier;

    CommandType(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier<Command> getSupplier() {
        return supplier;
    }
}
