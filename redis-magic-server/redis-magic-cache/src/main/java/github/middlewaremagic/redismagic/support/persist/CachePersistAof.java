package github.middlewaremagic.redismagic.support.persist;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import github.middlewaremagic.redismagic.api.ICache;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存持久化-AOF 持久化模式
 * @author gaoxiang
 */
@Slf4j
public class CachePersistAof<K,V> extends CachePersistAdaptor<K,V> {

    /**
     * 缓存列表
     */
    private final List<String> bufferList = new ArrayList<>();

    /**
     * 数据持久化路径
     */
    private final String dbPath;

    public CachePersistAof(String dbPath) {
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
        log.info("开始 AOF 持久化到文件");
        // 2. 持久化追加到文件中
        FileUtil.writeLines(bufferList,dbPath, CharsetUtil.CHARSET_UTF_8, true);

        // 3. 清空 buffer 列表
        bufferList.clear();
        log.info("完成 AOF 持久化到文件");
    }

    @Override
    public long delay() {
        return 1;
    }

    @Override
    public long period() {
        return 1;
    }

    @Override
    public TimeUnit timeUnit() {
        return TimeUnit.SECONDS;
    }

    /**
     * 添加文件内容到 buffer 列表中
     * @param json json 信息
     */
    public void append(final String json) {
        if(!StringUtil.isNullOrEmpty(json)) {
            bufferList.add(json);
        }
    }

}
