package github.middlewaremagic.redismagic.support.persist;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.model.PersistRdbEntry;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存持久化-db-基于 JSON
 * @author binbin.hou
 * @since 0.0.8
 */
public class CachePersistDbJson<K,V> extends CachePersistAdaptor<K,V> {

    /**
     * 数据库路径
     * @since 0.0.8
     */
    private final String dbPath;

    public CachePersistDbJson(String dbPath) {
        this.dbPath = dbPath;
    }

    /**
     * 持久化
     * key长度 key+value
     * 第一个空格，获取 key 的长度，然后截取
     * @param cache 缓存
     */
    @Override
    public void persist(ICache<K, V> cache) {
        Set<Map.Entry<K,V>> entrySet = cache.entrySet();
        FileWriter fileWriter = FileWriter.create(new File(dbPath), CharsetUtil.CHARSET_UTF_8);

        // 清空原文件
        fileWriter.write("" ,false);

        for(Map.Entry<K,V> entry : entrySet) {
            K key = entry.getKey();
            Long expireTime = cache.expire().expireTime(key);
            PersistRdbEntry<K,V> persistRdbEntry = new PersistRdbEntry<>();
            persistRdbEntry.setKey(key);
            persistRdbEntry.setValue(entry.getValue());
            persistRdbEntry.setExpire(expireTime);

            String line = JSONUtil.toJsonStr(persistRdbEntry);
            fileWriter.write(line, true);
        }
    }

    @Override
    public long delay() {
        return 5;
    }

    @Override
    public long period() {
        return 5;
    }

    @Override
    public TimeUnit timeUnit() {
        return TimeUnit.MINUTES;
    }

}
