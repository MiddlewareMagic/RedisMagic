package github.middlewaremagic.redismagic.cache.listener;

import github.middlewaremagic.redismagic.api.ICacheSlowListener;
import github.middlewaremagic.redismagic.api.ICacheSlowListenerContext;

/**
 * @author gaoxiang
 * @since 0.0.9
 */
public class MySlowListener implements ICacheSlowListener {

    @Override
    public void listen(ICacheSlowListenerContext context) {
        System.out.println("【慢日志】name: " + context.methodName());
    }

    @Override
    public long slowerThanMills() {
        return 0;
    }

}
