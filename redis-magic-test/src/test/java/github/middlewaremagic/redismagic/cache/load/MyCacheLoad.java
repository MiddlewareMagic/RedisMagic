package github.middlewaremagic.redismagic.cache.load;


import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.api.ICacheLoad;

/**
 * @author binbin.hou
 * @since 0.0.7
 */
public class MyCacheLoad implements ICacheLoad<String,String> {

    @Override
    public void load(ICache<String, String> cache) {
        cache.put("1", "1");
        cache.put("2", "2");
    }

}
