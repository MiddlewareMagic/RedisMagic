package github.middlewaremagic.redismagic.model;

/**
 * @program: `redisRewrite
 * @description: 双向链表节点
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-29 14:46
 */
public class DoubleListNode<K,V> {

    /**
     * 键
     * @since 0.0.12
     */
    private K key;

    /**
     * 值
     * @since 0.0.12
     */
    private V value;

    /**
     * 前一个节点
     * @since 0.0.12
     */
    private DoubleListNode<K,V> pre;

    /**
     * 后一个节点
     * @since 0.0.12
     */
    private DoubleListNode<K,V> next;

    public K key() {
        return key;
    }

    public DoubleListNode<K, V> key(K key) {
        this.key = key;
        return this;
    }

    public V value() {
        return value;
    }

    public DoubleListNode<K, V> value(V value) {
        this.value = value;
        return this;
    }

    public DoubleListNode<K, V> pre() {
        return pre;
    }

    public DoubleListNode<K, V> pre(DoubleListNode<K, V> pre) {
        this.pre = pre;
        return this;
    }

    public DoubleListNode<K, V> next() {
        return next;
    }

    public DoubleListNode<K, V> next(DoubleListNode<K, V> next) {
        this.next = next;
        return this;
    }
}
