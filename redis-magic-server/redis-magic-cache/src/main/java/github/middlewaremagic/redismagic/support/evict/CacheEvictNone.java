package github.middlewaremagic.redismagic.support.evict;

import github.middlewaremagic.redismagic.api.ICacheEntry;
import github.middlewaremagic.redismagic.api.ICacheEvictContext;

/**
 * 丢弃策略
 * @author gaoxiang
 * @since 0.0.2
 */
public class CacheEvictNone<K,V> extends AbstractCacheEvict<K,V> {

    @Override
    protected ICacheEntry<K, V> doEvict(ICacheEvictContext<K, V> context) {
        return null;
    }

}
