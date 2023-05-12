package github.middlewaremagic.redismagic.support.proxy.cglib;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.support.proxy.ICacheProxy;
import github.middlewaremagic.redismagic.support.proxy.bs.CacheProxyBs;
import github.middlewaremagic.redismagic.support.proxy.bs.CacheProxyBsContext;
import github.middlewaremagic.redismagic.support.proxy.bs.ICacheProxyBsContext;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 代理类
 * @author houbinbin
 * date 2019/3/7
 */
public class CglibProxy implements MethodInterceptor, ICacheProxy {

    /**
     * 被代理的对象
     */
    private final ICache target;

    public CglibProxy(ICache target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        ICacheProxyBsContext context = CacheProxyBsContext.newInstance()
                .method(method).params(params).target(target);

        return CacheProxyBs.newInstance().context(context).execute();
    }

    @Override
    public Object proxy() {
        Enhancer enhancer = new Enhancer();
        //目标对象类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        //通过 字节码技术 创建 目标对象类 的子类实例作为代理
        return enhancer.create();
    }

}
