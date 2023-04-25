package github.middlewaremagic.redismagic.support.interceptor.refresh;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.api.ICacheInterceptor;
import github.middlewaremagic.redismagic.api.ICacheInterceptorContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 刷新
 *
 * @author binbin.hou
 * @since 0.0.5
 */
@Slf4j
public class CacheInterceptorRefresh<K,V> implements ICacheInterceptor<K, V> {

    @Override
    public void before(ICacheInterceptorContext<K,V> context) {
        log.debug("Refresh start");
        final ICache<K,V> cache = context.cache();
        cache.expire().refreshExpire(cache.keySet());
    }

    @Override
    public void after(ICacheInterceptorContext<K,V> context) {
    }

}
