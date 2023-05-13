package github.middlewaremagic.redismagic.context.support;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.BeanFactory;
import github.middlewaremagic.redismagic.beans.factory.ConfigurableListableBeanFactory;
import github.middlewaremagic.redismagic.beans.factory.support.DefaultListableBeanFactory;

/*
 * ClassName: AbstractRefreshableApplicationContext
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
