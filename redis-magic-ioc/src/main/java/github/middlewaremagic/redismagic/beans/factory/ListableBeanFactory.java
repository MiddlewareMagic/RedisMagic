package github.middlewaremagic.redismagic.beans.factory;

import github.middlewaremagic.redismagic.beans.BeansException;

import java.util.Map;

/*
 * ClassName: ListableBeanFactory
 * Description:
 * @Author: zjh
 * @Create: 2023/5/12
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的 Bean 名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
