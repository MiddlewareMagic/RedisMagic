package github.middlewaremagic.redismagic.test.bean;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.*;
import github.middlewaremagic.redismagic.context.ApplicationContext;
import github.middlewaremagic.redismagic.context.ApplicationContextAware;

/*
 * ClassName: UserService
 * Description:
 * @Author: zjh
 * @Create: 2023/5/11
 */
public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private String uId;

    private UserDao userDao;

    private String company;

    private String location;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


}
