package github.middlewaremagic.redismagic.context;

import github.middlewaremagic.redismagic.beans.BeansException;
import github.middlewaremagic.redismagic.beans.factory.Aware;

/*
 * ClassName: ApplicationContextAware
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
