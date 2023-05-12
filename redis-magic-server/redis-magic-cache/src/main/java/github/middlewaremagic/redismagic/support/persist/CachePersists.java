package github.middlewaremagic.redismagic.support.persist;


import github.middlewaremagic.redismagic.api.ICachePersist;

/**
 * 缓存持久化工具类
 * @author gaoxiang
 */
public final class CachePersists {

    private CachePersists(){}

    /**
     * 无操作
     * @param <K> key
     * @param <V> value
     * @return 结果
     */
    public static <K,V> ICachePersist<K,V> none() {
        return new CachePersistNone<>();
    }

    /**
     * DB json 操作
     * @param <K> key
     * @param <V> value
     * @param path 文件路径
     * @return 结果
     */
    public static <K,V> ICachePersist<K,V> dbJson(final String path) {
        return new CachePersistDbJson<>(path);
    }

    /**
     * AOF 持久化
     * @param <K> key
     * @param <V> value
     * @param path 文件路径
     * @return 结果
     */
    public static <K,V> ICachePersist<K,V> aof(final String path) {
        return new CachePersistAof<>(path);
    }

}
