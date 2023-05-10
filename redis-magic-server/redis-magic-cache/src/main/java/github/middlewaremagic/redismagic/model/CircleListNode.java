package github.middlewaremagic.redismagic.model;

/**
 * 循环链表节点
 * @program: `redisRewrite
 * @description: 循环链表节点
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-29 14:46
 */
public class CircleListNode<K,V> {

    /**
     * 键
     * @since 0.0.15
     */
    private K key;

    /**
     * 值
     * @since 0.0.15
     */
    private V value = null;

    /**
     * 是否被访问过
     * @since 0.0.15
     */
    private boolean accessFlag = false;

    /**
     * 前一个节点
     * @since 0.0.15
     */
    private CircleListNode<K, V> pre;

    /**
     * 后一个节点
     * @since 0.0.15
     */
    private CircleListNode<K, V> next;

    public CircleListNode(K key) {
        this.key = key;
    }

    public K key() {
        return key;
    }

    public CircleListNode<K, V> key(K key) {
        this.key = key;
        return this;
    }

    public V value() {
        return value;
    }

    public CircleListNode<K, V> value(V value) {
        this.value = value;
        return this;
    }

    public boolean accessFlag() {
        return accessFlag;
    }

    public CircleListNode<K, V> accessFlag(boolean accessFlag) {
        this.accessFlag = accessFlag;
        return this;
    }

    public CircleListNode<K, V> pre() {
        return pre;
    }

    public CircleListNode<K, V> pre(CircleListNode<K, V> pre) {
        this.pre = pre;
        return this;
    }

    public CircleListNode<K, V> next() {
        return next;
    }

    public CircleListNode<K, V> next(CircleListNode<K, V> next) {
        this.next = next;
        return this;
    }

    @Override
    public String toString() {
        return "CircleListNode{" +
                "key=" + key +
                ", value=" + value +
                ", accessFlag=" + accessFlag +
                ", pre=" + pre +
                ", next=" + next +
                '}';
    }

}
