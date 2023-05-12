package github.middlewaremagic.redismagic.support.listener.remove;

import github.middlewaremagic.redismagic.api.ICacheRemoveListener;
import github.middlewaremagic.redismagic.api.ICacheRemoveListenerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的删除监听类
 * @author gaoxiang
 * @since 0.0.6
 */
@Slf4j
public class CacheRemoveListener<K,V> implements ICacheRemoveListener<K,V> {

    @Override
    public void listen(ICacheRemoveListenerContext<K, V> context) {
        log.debug("Remove key: {}, value: {}, type: {}",
                context.key(), context.value(), context.type());
    }

}
