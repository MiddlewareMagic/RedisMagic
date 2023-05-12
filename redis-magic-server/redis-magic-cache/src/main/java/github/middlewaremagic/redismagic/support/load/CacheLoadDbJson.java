package github.middlewaremagic.redismagic.support.load;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.api.ICacheLoad;
import github.middlewaremagic.redismagic.model.PersistRdbEntry;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 加载策略-文件路径
 * @author gaoxiang
 */
@Slf4j
public class CacheLoadDbJson<K,V> implements ICacheLoad<K,V> {

    /**
     * 文件路径
     */
    private final String dbPath;

    public CacheLoadDbJson(String dbPath) {
        this.dbPath = dbPath;
    }

    @Override
    public void load(ICache<K, V> cache) {
        // 如果文件很大 会不会出现问题 ?
        List<String> lines = FileUtil.readLines(dbPath, CharsetUtil.CHARSET_UTF_8);
        log.info("[load] 开始处理 path: {}", dbPath);
        if(CollectionUtil.isEmpty(lines)) {
            log.info("[load] path: {} 文件内容为空，直接返回", dbPath);
            return;
        }

        for(String line : lines) {
            if(StringUtil.isNullOrEmpty(line)) {
                continue;
            }

            // 执行
            // 简单的类型还行，复杂的这种反序列化会失败
            PersistRdbEntry<K,V> entry = JSONUtil.toBean(line, PersistRdbEntry.class);

            K key = entry.getKey();
            V value = entry.getValue();
            Long expire = entry.getExpire();

            cache.put(key, value);
            if(expire!= null) {
                cache.expireAt(key, expire);
            }
        }
        //nothing...
    }
}
