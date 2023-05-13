package github.middlewaremagic.redismagic.beans.factory.config;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.BeanFactory;

/*
 * ClassName: AutowireCapableBeanFactory
 * Description:
 * @Author: zjh
 * @Create: 2023/5/12
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
