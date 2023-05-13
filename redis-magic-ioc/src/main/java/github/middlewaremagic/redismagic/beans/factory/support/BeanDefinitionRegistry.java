package github.middlewaremagic.redismagic.beans.factory.support;

import github.middlewaremagic.redismagic.beans.factory.config.BeanDefinition;

/*
 * ClassName: BeanDefinitionRegistry
 * Description:
 * @Author: zjh
 * @Create: 2023/5/11
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断是否包含指定名称的 BeanDefinition
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

}
