package github.middlewaremagic.redismagic.context.event;

import github.middlewaremagic.redismagic.context.ApplicationEvent;
import github.middlewaremagic.redismagic.context.ApplicationListener;

/*
 * ClassName: ApplicationEventMulticaster
 * Description:
 * @Author: zjh
 * @Create: 2023/5/15
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
