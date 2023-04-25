package github.middlewaremagic.redismagic.api;

/**
 * @program: `redisRewrite
 * @description: 驱逐策略
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 11:19
 */
public interface ICacheEvict<K, V> {

    /**
     * 驱除策略
     *
     * @param context 上下文
     * @since 0.0.2
     * @return 被移除的明细，没有时返回 null
     */
    ICacheEntry<K,V> evict(final ICacheEvictContext<K, V> context);

    /**
     * 更新 key 信息
     * @param key key
     * @since 0.0.11
     */
    void updateKey(final K key);

    /**
     * 删除 key 信息
     * @param key key
     * @since 0.0.11
     */
    void removeKey(final K key);

}
