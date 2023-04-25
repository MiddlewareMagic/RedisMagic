package github.middlewaremagic.redismagic.support.interceptor.evict;

import github.middlewaremagic.redismagic.api.ICacheEvict;
import github.middlewaremagic.redismagic.api.ICacheInterceptor;
import github.middlewaremagic.redismagic.api.ICacheInterceptorContext;

import java.lang.reflect.Method;

/**
 * 驱除策略拦截器
 *
 * @author binbin.hou
 * @since 0.0.11
 */
public class CacheInterceptorEvict<K,V> implements ICacheInterceptor<K, V> {

    @Override
    public void before(ICacheInterceptorContext<K,V> context) {
    }

    @Override
    @SuppressWarnings("all")
    public void after(ICacheInterceptorContext<K,V> context) {
        ICacheEvict<K,V> evict = context.cache().evict();

        Method method = context.method();
        final K key = (K) context.params()[0];
        if("remove".equals(method.getName())) {
            evict.removeKey(key);
        } else {
            evict.updateKey(key);
        }
    }

}
