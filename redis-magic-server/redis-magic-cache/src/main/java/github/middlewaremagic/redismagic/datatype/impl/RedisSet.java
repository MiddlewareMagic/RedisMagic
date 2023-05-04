package github.middlewaremagic.redismagic.datatype.impl;

import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lilan
 */
public class RedisSet implements RedisData {

    private final Set<BytesWrapper> set = new HashSet<>();

    public int sadd(List<BytesWrapper> members)
    {
        return (int) members.stream().filter(set::add).count();
    }

    public Collection<BytesWrapper> keys()
    {
        return set;
    }

    public int srem(List<BytesWrapper> members)
    {
        return (int) members.stream().filter(set::remove).count();
    }
}
