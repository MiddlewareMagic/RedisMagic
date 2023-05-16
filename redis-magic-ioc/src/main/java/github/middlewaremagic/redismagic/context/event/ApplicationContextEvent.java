package github.middlewaremagic.redismagic.context.event;

import github.middlewaremagic.redismagic.context.ApplicationContext;
import github.middlewaremagic.redismagic.context.ApplicationEvent;

/*
 * ClassName: ApplicationContextEvent
 * Description:
 * @Author: zjh
 * @Create: 2023/5/15
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
