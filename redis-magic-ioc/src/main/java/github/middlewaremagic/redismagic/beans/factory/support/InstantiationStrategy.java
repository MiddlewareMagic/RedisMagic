package github.middlewaremagic.redismagic.beans.factory.support;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/*
 * ClassName: InstantiationStrategy
 * Description:
 * @Author: zjh
 * @Create: 2023/5/11
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
