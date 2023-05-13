package github.middlewaremagic.redismagic.beans.factory.config;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.ConfigurableListableBeanFactory;

/*
 * ClassName: BeanFactoryPostProcessor
 * Description:
 * @Author: zjh
 * @Create: 2023/5/12
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
