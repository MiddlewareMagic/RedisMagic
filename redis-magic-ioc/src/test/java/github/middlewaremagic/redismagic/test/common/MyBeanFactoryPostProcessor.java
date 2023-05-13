package github.middlewaremagic.redismagic.test.common;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.PropertyValue;
import github.middlewaremagic.redismagic.beans.PropertyValues;
import github.middlewaremagic.redismagic.beans.factory.ConfigurableListableBeanFactory;
import github.middlewaremagic.redismagic.beans.factory.config.BeanDefinition;
import github.middlewaremagic.redismagic.beans.factory.config.BeanFactoryPostProcessor;

/*
 * ClassName: MyBeanFactoryPostProcessor
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
