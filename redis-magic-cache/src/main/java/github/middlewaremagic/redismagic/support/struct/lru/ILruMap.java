package github.middlewaremagic.redismagic.support.struct.lru;

import github.middlewaremagic.redismagic.api.ICacheEntry;

/**
 * @program: `redisRewrite
 * @description: LRU map 接口
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 15:17
 **/
public interface ILruMap<K,V> {

    /**
     * 移除最老的元素
     * @return 移除的明细
     * @since 0.0.13
     */
    ICacheEntry<K, V> removeEldest();

    /**
     * 更新 key 的信息
     * @param key key
     * @since 0.0.13
     */
    void updateKey(final K key);

    /**
     * 移除对应的 key 信息
     * @param key key
     * @since 0.0.13
     */
    void removeKey(final K key);

    /**
     * 是否为空
     * @return 是否
     * @since 0.0.13
     */
    boolean isEmpty();

    /**
     * 是否包含元素
     * @param key 元素
     * @return 结果
     * @since 0.0.13
     */
    boolean contains(final K key);
}
