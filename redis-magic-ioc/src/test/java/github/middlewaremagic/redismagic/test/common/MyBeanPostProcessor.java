package github.middlewaremagic.redismagic.test.common;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.config.BeanPostProcessor;
import github.middlewaremagic.redismagic.test.bean.UserService;

/*
 * ClassName: MyBeanPostProcessor
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
