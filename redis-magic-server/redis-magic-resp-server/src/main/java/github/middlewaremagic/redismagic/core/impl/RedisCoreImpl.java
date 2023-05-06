package github.middlewaremagic.redismagic.core.impl;
/*
 * ClassName: RedisCoreImpl
 * Description:
 * @Author: zjh
 * @Create: 2023/5/5
 */

import github.middlewaremagic.redismagic.core.RedisCore;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class RedisCoreImpl implements RedisCore {

    /**
     * 客户端可能使用hash路由，更换为跳表更好的避免hash冲突
     */
    private final ConcurrentNavigableMap<BytesWrapper, RedisData> map = new ConcurrentSkipListMap<BytesWrapper, RedisData>();

    @Override
    public Set<BytesWrapper> keys() {
        return map.keySet();
    }

    @Override
    public boolean exist(BytesWrapper key) {
        return map.containsKey(key);
    }

    @Override
    public void put(BytesWrapper key, RedisData redisData) {
        map.put(key, redisData);
    }

    @Override
    public RedisData get(BytesWrapper key) {
        RedisData redisData = map.get(key);
        if (redisData == null) {
            return null;
        }
        return redisData;
    }

    @Override
    public long remove(List<BytesWrapper> keys) {
        return keys.stream().peek(map::remove).count();
    }

    @Override
    public void cleanAll() {
        map.clear();
    }
}
