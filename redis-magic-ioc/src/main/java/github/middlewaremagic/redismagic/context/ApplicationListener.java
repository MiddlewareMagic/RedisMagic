package github.middlewaremagic.redismagic.context;

import java.util.EventListener;

/*
 * ClassName: ApplicationListener
 * Description:
 * @Author: zjh
 * @Create: 2023/5/15
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
