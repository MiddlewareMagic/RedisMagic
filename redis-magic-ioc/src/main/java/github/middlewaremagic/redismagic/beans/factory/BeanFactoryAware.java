package github.middlewaremagic.redismagic.beans.factory;

import github.middlewaremagic.redismagic.beans.BeansException;

/*
 * ClassName: BeanFactoryAware
 * Description: 实现此接口，就能感知到所属的 BeanFactory
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
