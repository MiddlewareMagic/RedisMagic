package github.middlewaremagic.redismagic.api;

/**
 * 驱除策略
 *
 * 1. 新加的 key
 * 2. map 实现
 * 3. 淘汰监听器
 * @program: `redisRewrite
 * @description: 驱除策略, 应用于:
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 11:19
 */
public interface ICacheEvictContext<K,V> {

    /**
     * 新加的 key
     * @return key
     * @since 0.0.2
     */
    K key();

    /**
     * cache 实现
     * @return map
     * @since 0.0.2
     */
    ICache<K, V> cache();

    /**
     * 获取大小
     * @return 大小
     * @since 0.0.2
     */
    int size();

}
