package github.middlewaremagic.redismagic.context.event;

/*
 * ClassName: ContextClosedEvent
 * Description:
 * @Author: zjh
 * @Create: 2023/5/15
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
