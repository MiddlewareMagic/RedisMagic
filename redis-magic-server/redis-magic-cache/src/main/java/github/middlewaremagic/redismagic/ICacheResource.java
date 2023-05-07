package github.middlewaremagic.redismagic;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.bs.CacheBs;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;

/**
 * @program: `redisRewrite
 * @description: 妥协, 使用单例避免额外的包含关系
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-06 16:23
 **/
public class ICacheResource {

    public final static ICache<BytesWrapper, RedisData> iCache;

    static {
        iCache = CacheBs.<BytesWrapper, RedisData>newInstance().build();
    }
}
