package github.middlewaremagic.redismagic.support.proxy;

import cn.hutool.core.util.ObjectUtil;
import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.support.proxy.cglib.CglibProxy;
import github.middlewaremagic.redismagic.support.proxy.dynamic.DynamicProxy;
import github.middlewaremagic.redismagic.support.proxy.none.NoneProxy;

import java.lang.reflect.Proxy;

/**
 * @program: `redisRewrite
 * @description: 缓存代理实现类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 11:19
 */
public final class CacheProxy {

    private CacheProxy(){}

    /**
     * 获取对象代理
     * @param <K> 泛型 key
     * @param <V> 泛型 value
     * @param cache 对象代理
     * @return 代理信息
     * @since 0.0.4
     */
    @SuppressWarnings("all")
    public static <K,V> ICache<K,V> getProxy(final ICache<K,V> cache) {
        if(ObjectUtil.isNull(cache)) {
            return (ICache<K,V>) new NoneProxy(cache).proxy();
        }

        final Class clazz = cache.getClass();

        // 如果targetClass本身是个接口或者targetClass是JDK Proxy生成的,则使用JDK动态代理。
        // 参考 spring 的 AOP 判断
        if (clazz.isInterface() || Proxy.isProxyClass(clazz)) {
            return (ICache<K,V>) new DynamicProxy(cache).proxy();
        }

        return (ICache<K,V>) new CglibProxy(cache).proxy();
    }

}
