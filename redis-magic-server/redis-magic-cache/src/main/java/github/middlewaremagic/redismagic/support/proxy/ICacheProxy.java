package github.middlewaremagic.redismagic.support.proxy;

/**
 * @program: `redisRewrite
 * @description: 缓存代理接口
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-29 11:19
 */
public interface ICacheProxy {

    /**
     * 获取代理实现
     * @return 代理
     * @since 0.0.4
     */
    Object proxy();

}
