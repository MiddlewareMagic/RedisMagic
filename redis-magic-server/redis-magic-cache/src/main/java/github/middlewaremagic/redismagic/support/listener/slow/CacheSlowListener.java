package github.middlewaremagic.redismagic.support.listener.slow;

import cn.hutool.json.JSONUtil;
import github.middlewaremagic.redismagic.api.ICacheSlowListener;
import github.middlewaremagic.redismagic.api.ICacheSlowListenerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 慢日志监听类
 * @author gaoxiang
 * @since 0.0.9
 */
@Slf4j
public class CacheSlowListener implements ICacheSlowListener {



    @Override
    public void listen(ICacheSlowListenerContext context) {
        log.warn("[Slow] methodName: {}, params: {}, cost time: {}",
                context.methodName(), JSONUtil.toJsonStr(context.params()), context.costTimeMills());
    }

    @Override
    public long slowerThanMills() {
        return 1000L;
    }

}
