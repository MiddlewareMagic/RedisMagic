package github.middlewaremagic.redismagic.beans.factory;

/*
 * ClassName: BeanFactory
 * Description:
 * @Author: zjh
 * @Create: 2023/5/11
 */

import github.middlewaremagic.redismagic.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
