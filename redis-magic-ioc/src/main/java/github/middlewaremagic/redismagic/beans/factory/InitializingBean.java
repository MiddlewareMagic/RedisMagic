package github.middlewaremagic.redismagic.beans.factory;

/*
 * ClassName: InitializingBean
 * Description: 实现此接口的 Bean 对象，会在 BeanFactory 设置属性后做出相应处理，例如执行自定义初始化
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
