package github.middlewaremagic.redismagic.context.support;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.config.BeanPostProcessor;
import github.middlewaremagic.redismagic.context.ApplicationContext;
import github.middlewaremagic.redismagic.context.ApplicationContextAware;

/*
 * ClassName: ApplicationContextAwareProcessor
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
