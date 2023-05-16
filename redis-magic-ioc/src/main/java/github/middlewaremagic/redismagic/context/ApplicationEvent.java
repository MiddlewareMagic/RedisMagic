package github.middlewaremagic.redismagic.context;

import java.util.EventObject;

/*
 * ClassName: ApplicationEvent
 * Description:
 * @Author: zjh
 * @Create: 2023/5/15
 */
public class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
