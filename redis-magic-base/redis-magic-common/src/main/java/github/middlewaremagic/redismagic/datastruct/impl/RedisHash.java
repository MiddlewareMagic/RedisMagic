package github.middlewaremagic.redismagic.datastruct.impl;


import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaoxiang
 */
public class RedisHash implements RedisData {

    private final Map<BytesWrapper, BytesWrapper> map = new HashMap<>();

    public int put(BytesWrapper field, BytesWrapper value) {
        return map.put(field, value) == null ? 1 : 0;
    }

    public Map<BytesWrapper, BytesWrapper> getMap() {
        return map;
    }

    public int del(List<BytesWrapper> fields) {
        return (int) fields.stream().filter(key -> map.remove(key) != null).count();
    }
}
