package github.middlewaremagic.redismagic.core;

import github.middlewaremagic.redismagic.api.ICacheContext;
import github.middlewaremagic.redismagic.api.ICacheEvict;

import java.util.Map;

/**
 * @program: `redisRewrite
 * @description: 缓存上下文
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-29 14:40
 **/
public class CacheContext<K,V> implements ICacheContext<K,V> {

    /**
     * map 信息
     * @since 0.0.2
     */
    private Map<K, V> map;

    /**
     * 大小限制
     * @since 0.0.2
     */
    private int size;

    /**
     * 驱除策略
     * @since 0.0.2
     */
    private ICacheEvict<K,V> cacheEvict;

    @Override
    public Map<K, V> map() {
        return map;
    }

    public CacheContext<K, V> map(Map<K, V> map) {
        this.map = map;
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    public CacheContext<K, V> size(int size) {
        this.size = size;
        return this;
    }

    @Override
    public ICacheEvict<K, V> cacheEvict() {
        return cacheEvict;
    }

    public CacheContext<K, V> cacheEvict(ICacheEvict<K, V> cacheEvict) {
        this.cacheEvict = cacheEvict;
        return this;
    }
}
