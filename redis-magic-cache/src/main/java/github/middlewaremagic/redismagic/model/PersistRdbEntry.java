package github.middlewaremagic.redismagic.model;

/**
 * @program: `redisRewrite
 * @description: RDB 持久化明细
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 14:46
 */
public class PersistRdbEntry<K,V> {

    /**
     * key
     * @since 0.0.8
     */
    private K key;

    /**
     * value
     * @since 0.0.8
     */
    private V value;

    /**
     * expire
     * @since 0.0.8
     */
    private Long expire;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
