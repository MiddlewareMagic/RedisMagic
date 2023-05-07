package github.middlewaremagic.redismagic.datastruct.impl;

import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gaoxiang
 */
public class RedisSet implements RedisData {

    private final Set<BytesWrapper> set = new HashSet<>();

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
