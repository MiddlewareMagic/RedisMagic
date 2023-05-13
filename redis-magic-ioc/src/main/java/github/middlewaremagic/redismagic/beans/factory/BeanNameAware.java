package github.middlewaremagic.redismagic.beans.factory;

/*
 * ClassName: BeanNameAware
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
