package github.middlewaremagic.redismagic.beans.factory.config;

/*
 * ClassName: BeanReference
 * Description:
 * @Author: zjh
 * @Create: 2023/5/12
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
