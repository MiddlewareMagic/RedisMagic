package github.middlewaremagic.redismagic.context;

/*
 * ClassName: ApplicationEventPublisher
 * Description:
 * @Author: zjh
 * @Create: 2023/5/16
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
