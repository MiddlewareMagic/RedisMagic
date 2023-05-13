package github.middlewaremagic.redismagic.beans.factory;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.config.AutowireCapableBeanFactory;
import github.middlewaremagic.redismagic.beans.factory.config.BeanDefinition;
import github.middlewaremagic.redismagic.beans.factory.config.ConfigurableBeanFactory;

/*
 * ClassName: ConfigurableListableBeanFactory
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
