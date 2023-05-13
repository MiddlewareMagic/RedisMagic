package github.middlewaremagic.redismagic.context;

import github.middlewaremagic.redismagic.beans.BeansException;

/*
 * ClassName: ConfigurableApplicationContext
 * Description:
 * @Author: zjh
 * @Create: 2023/5/13
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
