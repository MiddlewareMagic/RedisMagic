package github.middlewaremagic.redismagic.beans.factory.config;

import github.middlewaremagic.redismagic.beans.factory.HierarchicalBeanFactory;

/*
 * ClassName: ConfigurableBeanFactory
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
