package github.middlewaremagic.redismagic.api;

import java.util.Map;

/**
 * @program: `redisRewrite
 * @description: 缓存上下文
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-29 11:19
 */
public interface ICacheContext<K, V> {

    /**
     * map 信息
     * @return map
     * @since 0.0.2
     */
    Map<K, V> map();

    /**
     * 大小限制
     * @return 大小限制
     * @since 0.0.2
     */
    int size();

    /**
     * 驱除策略
     * @return 策略
     * @since 0.0.2
     */
    ICacheEvict<K,V> cacheEvict();

}
