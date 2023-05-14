package github.middlewaremagic.redismagic.beans.factory;

/*
 * ClassName: FactoryBean
 * Description:
 * @Author: zjh
 * @Create: 2023/5/14
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
