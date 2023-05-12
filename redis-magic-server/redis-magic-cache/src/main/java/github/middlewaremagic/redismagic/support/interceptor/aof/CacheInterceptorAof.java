package github.middlewaremagic.redismagic.support.interceptor.aof;

import cn.hutool.json.JSONUtil;
import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.api.ICacheInterceptor;
import github.middlewaremagic.redismagic.api.ICacheInterceptorContext;
import github.middlewaremagic.redismagic.api.ICachePersist;
import github.middlewaremagic.redismagic.model.PersistAofEntry;
import github.middlewaremagic.redismagic.support.persist.CachePersistAof;
import lombok.extern.slf4j.Slf4j;

/**
 * 顺序追加模式
 *
 * AOF 持久化到文件，暂时不考虑 buffer 等特性。
 * @author gaoxiang
 */
@Slf4j
public class CacheInterceptorAof<K,V> implements ICacheInterceptor<K, V> {


    @Override
    public void before(ICacheInterceptorContext<K,V> context) {
    }

    @Override
    public void after(ICacheInterceptorContext<K,V> context) {
        // 持久化类
        ICache<K,V> cache = context.cache();
        ICachePersist<K,V> persist = cache.persist();

        if(persist instanceof CachePersistAof) {
            CachePersistAof<K,V> cachePersistAof = (CachePersistAof<K,V>) persist;

            String methodName = context.method().getName();
            PersistAofEntry aofEntry = PersistAofEntry.newInstance();
            aofEntry.setMethodName(methodName);
            aofEntry.setParams(context.params());

            String json = JSONUtil.toJsonStr(aofEntry);

            // 直接持久化
            log.debug("AOF 开始追加文件内容：{}", json);
            cachePersistAof.append(json);
            log.debug("AOF 完成追加文件内容：{}", json);
        }
    }

}
