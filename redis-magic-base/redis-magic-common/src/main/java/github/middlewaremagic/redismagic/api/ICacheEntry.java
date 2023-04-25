package github.middlewaremagic.redismagic.api;

/**
 * @program: `redisRewrite
 * @description: 缓存明细信息
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 11:19
 */
public interface ICacheEntry<K, V> {

    /**
     * @since 0.0.11
     * @return key
     */
    K key();

    /**
     * @since 0.0.11
     * @return value
     */
    V value();

}
