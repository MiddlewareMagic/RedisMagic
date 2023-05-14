package github.middlewaremagic.redismagic.beans.factory.config;

/*
 * ClassName: SingletonBeanRegistry
 * Description: 单例注册表
 * @Author: zjh
 * @Create: 2023/5/11
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
