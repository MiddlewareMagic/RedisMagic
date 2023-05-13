package github.middlewaremagic.redismagic.context.support;

import github.middlewaremagic.redismagic.beans.factory.support.DefaultListableBeanFactory;
import github.middlewaremagic.redismagic.beans.factory.xml.XmlBeanDefinitionReader;

/*
 * ClassName: AbstractXmlApplicationContext
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
