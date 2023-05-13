package github.middlewaremagic.redismagic.test.bean;

import github.middlewaremagic.redismagic.beans.factory.DisposableBean;
import github.middlewaremagic.redismagic.beans.factory.InitializingBean;

/*
 * ClassName: UserService
 * Description:
 * @Author: zjh
 * @Create: 2023/5/11
 */
public class UserService implements InitializingBean, DisposableBean {


    private String uId;

    private UserDao userDao;

    private String company;

    private String location;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
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
}
