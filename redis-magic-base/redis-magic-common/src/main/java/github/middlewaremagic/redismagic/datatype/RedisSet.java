package github.middlewaremagic.redismagic.datatype;/*
 * ClassName: RedisSet
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedisSet implements RedisData {
    private long timeout = -1;

    private final Set<BytesWrapper> set = new HashSet<>();

    @Override
    public long timeout() {
        return timeout;
    }

    @Override
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public int sadd(List<BytesWrapper> members) {
        return (int) members.stream().filter(set::add).count();
    }

    public Collection<BytesWrapper> keys() {
        return set;
    }

    public int srem(List<BytesWrapper> members) {
        return (int) members.stream().filter(set::remove).count();
    }
}

