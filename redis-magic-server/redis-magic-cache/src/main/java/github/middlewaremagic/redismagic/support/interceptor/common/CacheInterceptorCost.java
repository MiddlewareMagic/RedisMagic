package github.middlewaremagic.redismagic.support.interceptor.common;

import cn.hutool.core.collection.CollectionUtil;
import github.middlewaremagic.redismagic.api.ICacheInterceptor;
import github.middlewaremagic.redismagic.api.ICacheInterceptorContext;
import github.middlewaremagic.redismagic.api.ICacheSlowListener;
import github.middlewaremagic.redismagic.support.listener.slow.CacheSlowListenerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 耗时统计
 *
 * （1）耗时
 * （2）慢日志
 * @author gaoxiang
 * @since 0.0.5
 * @param <K> key
 * @param <V> value
 */
@Slf4j
public class CacheInterceptorCost<K,V> implements ICacheInterceptor<K,V> {

    @Override
    public void before(ICacheInterceptorContext<K,V> context) {
        log.debug("Cost start, method: {}", context.method().getName());
    }

    @Override
    public void after(ICacheInterceptorContext<K,V> context) {
        long costMills = context.endMills()-context.startMills();
        final String methodName = context.method().getName();
        log.debug("Cost end, method: {}, cost: {}ms", methodName, costMills);

        // 添加慢日志操作
        List<ICacheSlowListener> slowListeners = context.cache().slowListeners();
        if(CollectionUtil.isNotEmpty(slowListeners)) {
            CacheSlowListenerContext listenerContext = CacheSlowListenerContext.newInstance().startTimeMills(context.startMills())
                    .endTimeMills(context.endMills())
                    .costTimeMills(costMills)
                    .methodName(methodName)
                    .params(context.params())
                    .result(context.result())
                    ;

            // 设置多个，可以考虑不同的慢日志级别，做不同的处理
            for(ICacheSlowListener slowListener : slowListeners) {
                long slowThanMills = slowListener.slowerThanMills();
                if(costMills >= slowThanMills) {
                    slowListener.listen(listenerContext);
                }
            }
        }
    }

}
