package github.middlewaremagic.redismagic.beans.factory;

/*
 * ClassName: BeanClassLoaderAware
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
